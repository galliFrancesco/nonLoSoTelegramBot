package botpubblicita;

import TelegramAPI.Test;
import java.io.IOException;

/**
 *
 * @author galli_francesco
 */
public class BotPubblicita {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        // Lettura dei messaggi UTENTE
        getMessage messaggi = new getMessage();
        messaggi.run();
        
        // Invio di un messaggio
        sendMessage sMessage = new sendMessage();
        sMessage.run(); 
    }
    
}