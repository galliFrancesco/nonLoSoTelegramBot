package botpubblicita;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author galli_francesco
 */
public class openMap {
    
    String URLBase;
        
    public openMap(){
        URLBase = "https://nominatim.openstreetmap.org/";
        // https://nominatim.openstreetmap.org/search?q=mariano+comense,+monnet&format=xml&addressdetails=1
    }
    
    public void run(String azione) throws MalformedURLException, IOException, ParserConfigurationException, SAXException{
        
        System.out.println(""); 
        System.out.println(""); 
        
        String getUpdatesRequest = URLBase + azione;
        URL request = new URL(getUpdatesRequest); // fa la richiesta web
        
        String result = new BufferedReader(new InputStreamReader(request.openStream())).lines().collect(Collectors.joining("\n")); // prende il file XML
        //System.out.println(result);
        
        
        // lo scrive in un file, giusto per
        FileWriter fw = new FileWriter("AIUTO.xml"); 
        fw.write(result); 
        fw.close();    
        
        // lettura XML? 
        Document document; 
        
        DocumentBuilderFactory factory; 
        DocumentBuilder builder; 
        Element root; // W3C.DOC
        
        // Creazione dell'albero DOM dal coumento XML
        factory = DocumentBuilderFactory.newInstance(); 
        builder = factory.newDocumentBuilder(); 
        
        document = builder.parse("AIUTO.xml"); 
        root = document.getDocumentElement();
        
        System.out.println(root.getChildNodes()); 
       
    }
}
