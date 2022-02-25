/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramAPI;

import org.json.*;

/**
 *
 * @author galli_francesco
 */
public class TEST {
    public void foo(){
        
        String jsonString = "{nome: 'mario', messaggi:['ciao','mondo']}" ; //assign your JSON String here
        JSONObject obj = new JSONObject(jsonString);
        
        String name = obj.getString("nome");
        System.out.println(name);
        JSONArray arr = obj.getJSONArray("messaggi"); // notice that `"posts": [...]`
        
        for (int i = 0; i < arr.length(); i++)
        {
            // dammi la string in posizione i 
            String post_id = arr.getString(i); 
            System.out.println(post_id);
        }
    }
}
