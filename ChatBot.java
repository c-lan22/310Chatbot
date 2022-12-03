
import java.util.HashMap;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import org.json.simple.parser.ParseException;  


class ChatBot extends JFrame{
    public static HashMap<String,String> response;
    private static String conversation; 
    

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
        JFrame    wind     = new JFrame("");
        JMenuBar  menu     = new JMenuBar();
        // JMenuItem block    = new JMenuItem("Block...");
        // JMenuItem clear    = new JMenuItem("Clear");
        JButton   submit   = new JButton("send");
        JTextArea outgoing = new JTextArea();
        JTextArea incoming = new JTextArea();
        
        JPanel panno = new JPanel(new BorderLayout());
        conversation = "Hello I'm t3000 your digital help assistant \n";
        incoming.setText(conversation);
        submit.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
             String msg = outgoing.getText();
             if (msg.length() > 0) {
                conversation = conversation + msg  +"\n";
                incoming.setText(conversation );
                outgoing.setText("");
                
             }
           }
        });
        panno.add(outgoing);
        panno.add(submit, BorderLayout.EAST);
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,incoming, panno);
        split.setOneTouchExpandable(true);
        split.setDividerLocation(205);
                                                                           
        wind.getContentPane().setLayout(new BorderLayout());
        wind.getContentPane().add(split);
        wind.getContentPane().add(menu, BorderLayout.NORTH);
        wind.setSize(new Dimension(400,300));
        wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wind.setVisible(true);
        





        //create hash map of responses
        createResponses();


        
        System.out.println("Hello I'm t3000 your digital help assistant");
        try {
            System.out.println(BoredApi.getRandomActivity());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    private static void createResponses() {
        response = new HashMap<>();
        response.put("hi", "how can I help");
        response.put("hello", "how can I help");
        response.put("help", "");
        response.put("hey", "how can I help");
        response.put("hey", "how can I help");
    }
    public String getResponse(String userInput) {
        return "error";
    }
    
}
