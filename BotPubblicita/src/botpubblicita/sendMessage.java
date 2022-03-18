/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import TelegramAPI.Test;
import java.io.IOException;

/**
 *
 */
public class sendMessage {
    Test t = new Test();

    public void run() throws IOException {
        t.sendMessage("sendMessage?chat_id=233681681&text=prova2");
        
    }
}
