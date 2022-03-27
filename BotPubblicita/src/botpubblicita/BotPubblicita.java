package botpubblicita;

import TelegramAPI.Test;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author galli_francesco
 */
public class BotPubblicita {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, MalformedURLException, ParserConfigurationException, SAXException {

        ThreadLetturaMessagg tlm = new ThreadLetturaMessagg();

        tlm.start();
        
        
        readCSV rcsv = new readCSV(); 
        rcsv.run();
        
        //search?q=mariano+comense,+monnet&format=xml&addressdetails=1
    }
}
