package botpubblicita;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
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
        System.out.println(getUpdatesRequest); 
        
        URL request = new URL(getUpdatesRequest); // fa la richiesta web
        
        String result = new BufferedReader(new InputStreamReader(request.openStream())).lines().collect(Collectors.joining("\n")); // prende il file XML
        //System.out.println(result);
        
        
        // lo scrive in un file, giusto per
        FileWriter fw = new FileWriter("AIUTO.xml"); 
        fw.write(result); 
        fw.close();    
        // e il file viene scritto bene
        
        
        // lettura XML
        Document document; 
        
        DocumentBuilderFactory factory; 
        Element root, element; // W3C.DOC
        NodeList nodelist; 
        
        factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = factory.newDocumentBuilder();
        document = db.parse("AIUTO.xml"); 
        
        root = document.getDocumentElement();
        //List<String> giorgio = new ArrayList();  
        
        nodelist = root.getElementsByTagName("place"); // bi tag name se è lungo tutto il file
        
        if(nodelist!=null && nodelist.getLength() > 0){
            int numNode = nodelist.getLength();
            System.out.println("Ho degli elementi: " +numNode); 
            
            element = (Element)nodelist.item(0); 
            //System.out.println("++" + element.getElementsByTagName("town").item(0));
            
            
            NodeList nl = element.getElementsByTagName("town");
            
            String f = nl.item(0).getTextContent(); 
            
            System.out.println(f);
            
        }
        
        /*
        if(nodelist!=null && nodelist.getLength() > 0){
            int numNode = nodelist.getLength();
            
            element = (Element) nodelist.item(1); // secondo tag
            String s = element.getAttribute("time");
            
            for(int i = 2; i< numNode; i++){
                element = (Element) nodelist.item(i);
                dato = getInfo(element);
                cambio.add(dato);
            }
        }
        */
        
        
        /*
        DocumentBuilderFactory factory; 
        DocumentBuilder builder; 
        Element root, element; // W3C.DOC
        
        // Creazione dell'albero DOM dal coumento CMl
        factory = DocumentBuilderFactory.newInstance(); 
        builder = factory.newDocumentBuilder(); 
        
        document = builder.parse(filename); 
        root = document.getDocumentElement();
        
        List<Change> cambio = new ArrayList();  
        
        Change dato; 
        
        nodelist = root.getElementsByTagName("Cube");
        nodelist = ((Element)nodelist.item(1)).getElementsByTagName("Cube");
        */

        // Creazione dell'albero DOM dal coumento XML
        /*
        factory = DocumentBuilderFactory.newInstance(); 
        builder = factory.newDocumentBuilder(); 
        
        document = builder.parse("AIUTO.xml"); 
        */
        
        /* SOURCE -> STACKOVERFLOW
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File("input.xml"));
        NodeList nodeList = document.getElementsByTagName("Item");
        for(int x=0,size= nodeList.getLength(); x<size; x++) {
            System.out.println(nodeList.item(x).getAttributes().getNamedItem("name").getNodeValue());
        }
        */
        
       
    }
}
