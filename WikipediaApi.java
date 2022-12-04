import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/* 
 * Uses the wikipedia api to get info for converstations
 * https://en.wikipedia.org/api/rest_v1/#/Page%20content/get_page_summary__title_
 * 
 */

public class WikipediaApi {
    private static String wikiextract;
 
    public static String getWikipediaDescription(String title) {
        try {
            //bored api url
            
            URL url = new URL("https://en.wikipedia.org/api/rest_v1/page/summary/"+title);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            
            int myResponseCode = con.getResponseCode();
            
            //read in response
            StringBuffer response = new StringBuffer();
            String readLine = null;
            if (myResponseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((readLine = in .readLine()) != null) {
                    response.append(readLine);
                } in .close();
                
            } else {
                return "Wikipedia api didn't work, Its their fault not mine!! Your query was: \""+title+"\"";
            }
            String wikiPage = response.toString();
            //{"activity":"Shred old documents you don't need anymore","type":"busywork","participants":1,"price":0,"link":"","key":"2430066","accessibility":0}
            
            //make response into a json object
            JSONParser parser = new JSONParser();
            JSONObject context = (JSONObject) parser.parse(wikiPage);
            
            //get String out of json object
            wikiPage = (String) context.get("description");
            wikiextract = (String) context.get("extract");
            try {
                wikiPage.equals(null);
            } catch (NullPointerException e) {
                return Integer.toString(myResponseCode);
            }
            
            return wikiPage;
        } catch (IOException e) {
            //con didn't work
            return "connection didn't work";
        } catch (ParseException e) {
            // Json didn't parsde
            return "Json messed up";
        }
        
    }

    public static String getWikipediaExtract() {
        String[] words = wikiextract.split(" ");
        String betterFormat = "";
        for (int i = 0; i < words.length;i++) {
            if(i % 10 == 0 && i > 0){
                betterFormat = betterFormat + "\n";
            }
            betterFormat = betterFormat + words[i] + " ";
        }
        return betterFormat;
    }

    
}
