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
    public static String getWikipedia() {
        try {
            //bored api url
            
            URL url = new URL("http://www.boredapi.com/api/activity/");
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
                return "Bored api didn't work, Its their fault not mine!!";
            }
            String activity = response.toString();
            //{"activity":"Shred old documents you don't need anymore","type":"busywork","participants":1,"price":0,"link":"","key":"2430066","accessibility":0}
            
            //make response into a json object
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(activity);
            
            //get String out of json object
            activity = (String) json.get("activity");
            
            return activity;
        } catch (IOException e) {
            //con didn't work
            return "connection didn't work";
        } catch (ParseException e) {
            // Json didn't parsde
            return "Json messed up";
        }
        
    }

    
}
