package botpubblicita;

import TelegramAPI.Test;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author galliFrancesco
 * @brief Ogni tot secondi(10) richiede gli update
 */
public class ThreadLetturaMessagg extends Thread {

    Test t = new Test();

    Long id_chat;

    Long id_messaggio;  // è un numero identificativo e unico, quindi non si può sbagliare
    Long id_messaggio2 = 0L;

    String testo = "";

    @Override
    public void run() {

        System.out.println("Thread Partito");

        do {
            // Richiede gli update
            try {
                t.getUpdates("getUpdates");
                testo = t.getTesto();
                id_chat = t.getIdChat();
                id_messaggio = t.getIdMessaggio();

            } catch (IOException ex) {
                Logger.getLogger(ThreadLetturaMessagg.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Ok legge i messaggi, 
            System.out.println(testo);

            //if(!testoPrec.equals(testo)){}  // Se non è uguale a quello di adesso 
            // richiama il metodo per dire che c'è un messaggio diverso       
            
            if (id_messaggio != id_messaggio2) { //Se sono uguali, allora non si hanno inviato nuovi messaggi 
                // MEGLIO INCLUDERE ANCHE L'ID DEL MESSAGGIO -> se è lo stesso, non fa le cose 

                // Varie possibilità di messaggi 
                // Saluto(Era per testare, ma può essere un easter Egg)
                if (testo.equals("Ciao")) {
                    try {
                        t.sendMessage("sendMessage?chat_id=" + id_chat + "&text=Ciao");
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadLetturaMessagg.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                // Impostazione della città
                if (testo.equals("/citta")) {
                    try {
                        
                        // qui c'è da richiamare la cosa che fa con OpenMap
                        t.sendMessage("sendMessage?chat_id=" + id_chat + "&text=Dove abiti?");
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadLetturaMessagg.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
              
                id_messaggio2 = id_messaggio; // così dovrebbe farlo una sola volta
                
            } 
            // "Addormenta" il Thread, altrimenti farebbe troppi update
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadLetturaMessagg.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (true);
    }
}
