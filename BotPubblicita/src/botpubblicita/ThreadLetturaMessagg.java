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
 * @brief Ogni tot secondi(10) richiede gli update
 */
public class ThreadLetturaMessagg extends Thread {

    openMap map = new openMap();

    Test t = new Test();

    String utente;
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
            System.out.println(id_chat + ": " + testo);

            //if(!testoPrec.equals(testo)){}  // Se non è uguale a quello di adesso 
            // richiama il metodo per dire che c'è un messaggio diverso       
            if (id_messaggio != id_messaggio2) { //Se sono uguali, allora non si hanno inviato nuovi messaggi 

                // MEGLIO INCLUDERE ANCHE L'ID DEL MESSAGGIO -> se è lo stesso, non fa le cose 
                id_messaggio2 = id_messaggio; // così dovrebbe farlo una sola volta

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
