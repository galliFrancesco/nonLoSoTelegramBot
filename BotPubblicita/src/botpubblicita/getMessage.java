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
public class getMessage {
    Test t = new Test();

    public void run() throws IOException {
        t.getUpdates("getUpdates");  
    }
}
