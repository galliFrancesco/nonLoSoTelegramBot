package TelegramAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import org.json.*;

/**
 * @brief Gestire le API telegram
 * @author galli_francesco
 */
public class Test {

    String URLBase; 

    // Queste variabili vengono lette dal messaggio e verranno poi trasmesse con il get
    String nickutente, testo;
    Long id_chat, id_message;

    public Test() {
        URLBase = "https://api.telegram.org/bot5166569514:AAFfr4N1FeRgUIMPzI3HIKaNNbkzWAaDRqw/"; // url del mio bot
        // totally not my token, take IT... it's public
    }

    /**
     * 
     * @param azione -> https:[telegramBot]/azione
     * @brief Questo metodo è in grado di leggere i messaggi lasciati nella pagina getUpdates dal Bot
     */
    public void getUpdates(String azione) throws MalformedURLException, IOException {

        String getUpdatesRequest = URLBase + azione; // Completa l'inidirizzo URL 
        URL request = new URL(getUpdatesRequest);

        /**
         * --------
         *  LEGGERE I JSON
         *  --------
         */      
        String result = new BufferedReader(new InputStreamReader(request.openStream())).lines().collect(Collectors.joining("\n"));
        //System.out.println(result);
        String jsonString = result; //assign your JSON String here
        JSONObject obj = new JSONObject(jsonString);
        JSONArray arrResult = obj.getJSONArray("result"); 

        // Legge il JSON 
        for (int i = 0; i < arrResult.length(); i++) {
            //Ho ilmessaggio completo 
            JSONObject totale = arrResult.getJSONObject(i);

            //entro nel messaggio
            JSONObject message = totale.getJSONObject("message");
            id_message = message.getLong("message_id");
            testo = message.getString("text");
            //System.out.println(message);

            //entro nel from(CHI ha mandato il messaggio)
            JSONObject from = message.getJSONObject("from");
            id_chat = from.getLong("id");
            nickutente = from.getString("first_name");
            //System.out.println(first_name);

        }
    }


    /**
     * 
     * @param azione -> Sempre la stringa che dice cosa fare con l'URL
     * @brief Prende in input una stringa e la invia 
     */
    public void sendMessage(String azione) throws MalformedURLException, IOException {

        String getUpdatesRequest = URLBase + azione;
        URL request = new URL(getUpdatesRequest);

        String result = new BufferedReader(new InputStreamReader(request.openStream())).lines().collect(Collectors.joining("\n"));
        //String jsonString = result; //assign your JSON String here
       //JSONObject obj = new JSONObject(jsonString);
    }

    public String getTesto() {
        return testo;
    }
    public Long getIdChat() {
        return id_chat;
    }
    public Long getIdMessaggio() {
        return id_message;
    }
    public String getNickUtente(){
        return nickutente;  
    }
}