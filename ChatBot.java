
import java.util.HashMap;
import java.awt.*;   
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



class ChatBot extends JFrame{
    private static HashMap<String,String> response;
    private static String conversation; 
    private static String username;
    
    

    public static void main(String[] args) { 
        // box wind = new box();
        // //JFrame wind = new JFrame();
        // JTextField input = new JTextField();
        // input.setBounds(5,300, 300, 20);
        // wind.add(input);      
        // wind.setSize(400,500);//400 width and 500 height  
        // wind.setLayout(null);//using no layout managers  
        // wind.setVisible(true);

        // JTextArea area=new JTextArea("Welcome to javatpoint");  
        // area.setBounds(10,30, 200,200);  
        // wind.add(area);  
        // wind.setSize(300,300);
        
        /* 
         * Start creating the gui
         * 
         */

        JFrame    wind     = new JFrame("Richard Nixon ChatBot system");
        JMenuBar  menu     = new JMenuBar();
        JButton   submit   = new JButton("Send");
        JTextArea userText = new JTextArea();
        JTextArea chat = new JTextArea();
        JScrollPane sp = new JScrollPane(chat);
        JPanel panno = new JPanel(new BorderLayout());
        
        //chat.setBackground(Color.BLUE);
        chat.setBackground(Color.LIGHT_GRAY);
        userText.setBackground(Color.lightGray);
        //panno.setBackground(Color.BLACK);
        wind.setBackground(Color.BLACK);
        submit.setBackground(Color.RED);
        menu.setBackground(Color.BLACK);
        //set username to user
        username = "User";
        //create hash map of responses
        createResponses();
        //create array of confused responses
        String[] confusedBot = {
            "I don't understand","what do you mean??",
            "I'm not that smart","I am Richard Nixon and I don't understand you...",
            "you don't make any sense!!","your generation doesn't make any sense!",
            "I'm sending you to Nam."
        };
        String[] endInterview ={
            "Watergate is fake news!!","don't ask me about watergate",
            "they set me up","this interview is over", "I'm sending you to Nam."
        };

        /* 
         * Implementation of the chat
         */
        
        conversation = "<Richard Nixon>\nHello I'm Richard Nixon your digital help assistant \n";
        chat.setText(conversation);
        submit.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
             String msg = userText.getText();
             if (msg.length() > 0) {
                conversation = conversation +"<"+username+">\n"+ msg  +"\n";
                
                /* 
                 * create the bot response
                 */
                String chatBotResponse = "";
                
                if(msg.contains("Watergate")||msg.contains("watergate") ){     //calls watergate response
                                                                        
                    int rand = (int)(Math.random() * endInterview.length);
                    chatBotResponse = endInterview[rand];

                }else if(msg.contains("my name is")||msg.contains("My name is")){
                    String[] msgName = msg.split(" ");
                    username = msgName[3]; 
                    chatBotResponse = "hello "+username+"! /nwhat can I do for you?" ;
                }else if (msg.contains("what is a")||msg.contains("What is a")) {                            //calls wikipedia
                    String wikiTitle = "";
                    String[] wiki = msg.split(" ");
                    for (int i = 3; i < wiki.length;i++ ) {
                        if(i > 3){
                            wikiTitle = wikiTitle+"_";
                        }
                        wikiTitle = wikiTitle + wiki[i];
                    }
                    if(wikiTitle.equals("")){
                        chatBotResponse = "I am confused";
                    }else{
                        chatBotResponse = WikipediaApi.getWikipediaDescription(wikiTitle);
                    }
                    
                }else if(msg.contains("tell me more")||msg.contains("Tell me more")){             //call wiki tell more
                    chatBotResponse = WikipediaApi.getWikipediaExtract();
                } else if (msg.contains("suggest activity")||msg.contains("Suggest activity")) {     //calls boredapi
                    if(msg.contains(" for")){
                        String[] msgArray = msg.split(" ");
                        int numOfPeople = 0;
                        for (int  i = 2; i < msgArray.length;i++ ) {
                            try {
                                numOfPeople= Integer.parseInt( msgArray[i]);
                                break;
                            } catch (NumberFormatException f) {}
                        }
                        if (numOfPeople == 1) {
                            chatBotResponse = "I think you guys should " + BoredApi.getRandomActivity(numOfPeople).toLowerCase() + "!" ;
                        }else{
                            chatBotResponse = "I think you guys should " + BoredApi.getRandomActivity(numOfPeople).toLowerCase() + "!" ;
                        }
                    }else{
                        chatBotResponse = "I think you should " + BoredApi.getRandomActivity().toLowerCase() + "!";
                    }
                } else if(response.containsKey(msg) ){                     //calls hashmap    
                    chatBotResponse = response.get(msg);
                }else{                                                       //calls confused response
                    int rand = (int)(Math.random() * confusedBot.length);
                    chatBotResponse = confusedBot[rand];
                }
                

                conversation = conversation + "<Richard Nixon>\n"+ chatBotResponse +"\n";
                chat.setText(conversation );
                userText.setText("");
                
             }
           }
        });

        /* 
         * 
         * Finish up the GUI
         * 
         */
        panno.add(userText);
        panno.add(submit, BorderLayout.EAST);
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,sp, panno);
        split.setOneTouchExpandable(true);
        split.setDividerLocation(405);
                                                                           
        wind.getContentPane().setLayout(new BorderLayout());
        wind.getContentPane().add(split);
        wind.getContentPane().add(menu, BorderLayout.NORTH);
        wind.setSize(new Dimension(400,500));
        wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wind.setVisible(true);
        

    }
    private static void createResponses() {
        response = new HashMap<>();
        response.put("hi", "how can I help you and this country?");
        response.put("hello", "how can I help you and this country?");
        response.put("help", "");
        response.put("hey", "how can I help you and this country?");
    
    }
    public String getResponse(String userInput) {
        return "error";
    }
    
}
