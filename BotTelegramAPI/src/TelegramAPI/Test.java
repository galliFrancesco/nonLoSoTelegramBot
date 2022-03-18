package TelegramAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import org.json.*;

/**
 * 
 */
public class Test {
    String URLBase;

    public Test() {
        URLBase = "https://api.telegram.org/bot5166569514:AAFfr4N1FeRgUIMPzI3HIKaNNbkzWAaDRqw/"; // url del mio bot
    }
    
    public void getUpdates(String azione) throws MalformedURLException, IOException {
        
        String getUpdatesRequest = URLBase + azione;
        URL request = new URL(getUpdatesRequest);
        
        
        String result = new BufferedReader(new InputStreamReader(request.openStream())).lines().collect(Collectors.joining("\n"));
        System.out.println(result);
        String jsonString = result; //assign your JSON String here
        JSONObject obj = new JSONObject(jsonString);
        JSONArray arrResult = obj.getJSONArray("result"); // notice that `"posts": [...]`
        
        // SOUT -> Messaggio /n NoomeUtente
        for (int i = 0; i < arrResult.length(); i++) {
            //ho il totale
            JSONObject totale = arrResult.getJSONObject(i);
            //entro nel messaggio
            JSONObject message = totale.getJSONObject("message");
            long message_id = message.getLong("message_id");
            String text = message.getString("text");
            System.out.println(text);
            //entro nel from
            JSONObject from = message.getJSONObject("from");
            long id_chat = from.getLong("id");
            String first_name = from.getString("first_name");
            System.out.println(first_name);

        }
    }

    public void sendMessage(String azione) throws MalformedURLException, IOException {
        
        String getUpdatesRequest = URLBase + azione;
        URL request = new URL(getUpdatesRequest);

        String result = new BufferedReader(new InputStreamReader(request.openStream())).lines().collect(Collectors.joining("\n"));
        String jsonString = result; //assign your JSON String here
        JSONObject obj = new JSONObject(jsonString);
        //JSONArray arrResult = obj.getJSONArray("result"); // notice that `"posts": [...]`
        
        //for (int i = 0; i < arrResult.length(); i++) {
//            //ho il totale
//            JSONObject totale = arrResult.getJSONObject(i);
//            //entro nel messaggio
//            JSONObject message = totale.getJSONObject("message");
//            long message_id = message.getLong("message_id");
//            String text = message.getString("text");
//            System.out.println(text);
//            //entro nel from
//            JSONObject from = message.getJSONObject("from");
//            long id_chat = from.getLong("id");
//            String first_name = from.getString("first_name");
//            System.out.println(first_name);
//
        //}
    }

    

}
