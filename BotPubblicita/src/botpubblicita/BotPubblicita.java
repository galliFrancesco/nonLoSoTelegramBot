package botpubblicita;

import TelegramAPI.Test;
import java.io.IOException;

/**
 *
 * @author galli_Francesco
 */
public class BotPubblicita {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        // Inizializza la cosa per leggere i messaggi (ENTRATA)
        getMessage messaggi = new getMessage();
        messaggi.run();
        
        // Porvo a inviare un messaggio
        sendMessage sMessage = new sendMessage();
        sMessage.run(); 
    }
    
}
