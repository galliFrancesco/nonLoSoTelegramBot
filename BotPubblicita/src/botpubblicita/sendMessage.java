package botpubblicita;

import TelegramAPI.Test;
import java.io.IOException;

/**
 *
 * @author galli_francesco
 */
public class sendMessage {
    Test t = new Test();

    public void run() throws IOException {
        t.sendMessage("sendMessage?chat_id=233681681&text=prova2");     
    }
}
