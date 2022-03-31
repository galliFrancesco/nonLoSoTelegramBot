package botpubblicita;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author galliFrancesco
 */
public class BotPubblicita {

    /**
     * @Brief Semplicemente avvia il Thread per leggere i messaggi inviati al bot 
     */
    public static void main(String[] args) throws IOException, MalformedURLException, ParserConfigurationException, SAXException {

        ThreadLetturaMessagg tlm = new ThreadLetturaMessagg();

        tlm.start();
    }
}
