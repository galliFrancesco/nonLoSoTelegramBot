package botpubblicita;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author galli_francesco
 */
public class openMap {
    
    String URLBase;
        
    public openMap(){
        URLBase = "https://nominatim.openstreetmap.org/";
        // https://nominatim.openstreetmap.org/search?q=mariano+comense,+monnet&format=xml&addressdetails=1
    }
    
    public void run(String azione) throws MalformedURLException, IOException{
        
        System.out.println(""); 
        System.out.println(""); 
        
        String getUpdatesRequest = URLBase + azione;
        URL request = new URL(getUpdatesRequest); // fa la richiesta web
        
        String result = new BufferedReader(new InputStreamReader(request.openStream())).lines().collect(Collectors.joining("\n")); // prende il file XML
        System.out.println(result);
        
        // non posso usare JSON, è uno XML -.-
        /*
        JSONObject obj = new JSONObject(result); 
        JSONArray arrResult = obj.getJSONArray("result"); // mette il tutto in un array (?)
        
        for (int i = 0; i < arrResult.length(); i++) {
            //ho il totale
            JSONObject totale = arrResult.getJSONObject(i);
            System.out.println(totale); 
            /*
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
            

        }*/
    }
}
