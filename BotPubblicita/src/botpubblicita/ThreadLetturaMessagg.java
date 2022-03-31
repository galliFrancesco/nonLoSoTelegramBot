package botpubblicita;

import TelegramAPI.Test;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * @author galliFrancesco
 * @brief Ogni tot secondi(10) richiede gli update(Lettura nuovi messaggi)
 */
public class ThreadLetturaMessagg extends Thread {

    Test t = new Test();
    openMap map = new openMap(); 

    String utente;
    Long id_chat;

    Long id_messaggio;  // è un numero identificativo e unico del messaggio, quindi non si può sbagliare
    Long id_messaggio2 = 0L; // Variabile Temporanea

    String testo = "";

    @Override
    public void run() {

        // Un avviso perchè non si sa mai 
        System.out.println("Thread Partito");

        do {
            // Richiede gli update grazie alla libreria Test.java
            try {
                t.getUpdates("getUpdates");
                // Una volta letto prende le sue informazioni 
                testo = t.getTesto();
                id_chat = t.getIdChat();
                id_messaggio = t.getIdMessaggio();

            } catch (IOException ex) {
                Logger.getLogger(ThreadLetturaMessagg.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(id_chat + ": " + testo);

            //Se sono uguali, allora non si hanno nuovi messaggi 
            if (id_messaggio != id_messaggio2) { 
                // MEGLIO INCLUDERE ANCHE L'ID DEL MESSAGGIO -> se è lo stesso, non fa le cose 
                id_messaggio2 = id_messaggio; // così dovrebbe farlo una sola volta

                // Varie possibilità di messaggi 
                // Saluto(Era per testare, ma può essere un easter Egg :3)
                if (testo.equals("Ciao")) {
                    try {
                        t.sendMessage("sendMessage?chat_id=" + id_chat + "&text=Ciao");
                        // Così alla scritta "Ciao" risponderà con "Ciao"
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadLetturaMessagg.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // Impostazione della città (text="/citta")
                if (testo.equals("/citta")) {
                    try {
                        // Richiamo di OpenMap
                        t.sendMessage("sendMessage?chat_id=" + id_chat + "&text=Dove abiti?");
                        t.sendMessage("sendMessage?chat_id=" + id_chat + "&text=(ex. Longone al Segrino, Como)");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ThreadLetturaMessagg.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(ThreadLetturaMessagg.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    
                    
                    // Qui prende il messaggio, quindi aspettare finchè l'id non è diverso 
                    do {
                        
                        try {
                            // Cambierà il messaggio
                            t.getUpdates("getUpdates");
                            testo = t.getTesto();
                            id_messaggio = t.getIdMessaggio();
                            utente = t.getNickUtente(); 
                            
                        } catch (IOException ex) {
                            Logger.getLogger(ThreadLetturaMessagg.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } while (id_messaggio == id_messaggio2);
                    
                    
                    // Aspetta il messaggio dopo
                    // che dovrebbe generare la query
                    testo = testo.replaceAll(" ", "+"); // Imposta gli spazi come "+" per impostare la query

                    // passo la query alla classe della mappa
                    try {
                        // EX: // https://nominatim.openstreetmap.org/search?q=mariano+comense,+monnet&format=xml&addressdetails=1
                        map.run("search?q=" + testo + "&format=xml&addressdetails=1", utente, id_chat);

                    } catch (MalformedURLException | ParserConfigurationException | SAXException ex) {
                        Logger.getLogger(ThreadLetturaMessagg.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadLetturaMessagg.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

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
