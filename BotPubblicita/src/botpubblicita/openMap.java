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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author galliFrancesco
 * 
 * @brief Passa la query a openMap, che restituisce uno xml 
 *        Questa classe legge e "trasmuta le informazioni"
 */
public class openMap {
    
    readCSV rcsv = new readCSV(); 
    
    String URLBase;    
    String utente, id_utente; 
    
    
    public openMap(){
        URLBase = "https://nominatim.openstreetmap.org/";
        // https://nominatim.openstreetmap.org/search?q=mariano+comense,+monnet&format=xml&addressdetails=1
    }
    
    /**
     * @param azione -> Serve per completare il link con la query
     * @param utente -> Nome utente
     * @param id_utente -> L'ID che servirà poi per andare a modificare il CSV
     */
    public void run(String azione, String utente, Long id_chat) throws MalformedURLException, IOException, ParserConfigurationException, SAXException{
        
        System.out.println(""); 
        System.out.println(""); 
        
        String getUpdatesRequest = URLBase + azione;
        System.out.println(getUpdatesRequest); 
        
        URL request = new URL(getUpdatesRequest); // fa la richiesta web
        
        String result = new BufferedReader(new InputStreamReader(request.openStream())).lines().collect(Collectors.joining("\n")); // prende il file XML
        //System.out.println(result);
        
        
        // lo scrive in un file XML
        FileWriter fw = new FileWriter("temp.xml"); 
        fw.write(result); 
        fw.close();    
        
        // -------------
        // LETTURA DEL FILE XML DATO UN DOCUMENT
        // -------------
        Document document; 
        
        DocumentBuilderFactory factory; 
        Element root, element; // W3C.DOC
        NodeList nodelist; 
        
        factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        document = db.parse("temp.xml"); 
        
        root = document.getDocumentElement();
        
        nodelist = root.getElementsByTagName("place"); 
        // by tag name -> <place>
        //  <altre cose></altre cose>
        // </place>
        
        if(nodelist!=null && nodelist.getLength() > 0){
            int numNode = nodelist.getLength();
            System.out.println("Ho degli elementi: " +numNode); 
            
            Element e = (Element)nodelist.item(0);
            
            System.out.println(e.getAttribute("lat")); 
            System.out.println(e.getAttribute("lon"));    
            String lat = e.getAttribute("lat");
            String longi = e.getAttribute("lon");
            // Prende gli attributi di <place> e ne prende la latitudine e la longitudine
            
            //System.out.println(e.getAttribute("lat"));                   
            //System.out.println(e.getAttribute("lon"));    
            

            // NON SERVE, MA
            // Prende gli oggetti da <town> [...] </town>
            element = (Element)nodelist.item(0); 
            NodeList nl = element.getElementsByTagName("town");
            String f = nl.item(0).getTextContent(); 
            System.out.println(f);
            
            // TODO: Salvare le informazioni in un CSV
            // -> magari faccio la classe CSV, che si occupa anche di controllare che l'utente non sia già registarto(id_chat,nome, lat, lon)         
            rcsv = new readCSV(utente, id_chat, lat, longi); 
            rcsv.run(); 
            
        }   
    }
}