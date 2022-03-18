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
import org.w3c.dom.Element;

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
    
    public void run(String azione) throws MalformedURLException, IOException, ParserConfigurationException{
        
        System.out.println(""); 
        System.out.println(""); 
        
        String getUpdatesRequest = URLBase + azione;
        URL request = new URL(getUpdatesRequest); // fa la richiesta web
        
        String result = new BufferedReader(new InputStreamReader(request.openStream())).lines().collect(Collectors.joining("\n")); // prende il file XML
        System.out.println(result);
        
        // lo scrive in un file, giusto per
        FileWriter fw = new FileWriter("AIUTO.xml"); 
        fw.write(result); 
        fw.close();
        
        
        // lettura XML? 
        DocumentBuilderFactory factory; 
        DocumentBuilder builder; 
        Element root, element; // W3C.DOC
        
        // Creazione dell'albero DOM dal coumento XMl
        factory = DocumentBuilderFactory.newInstance(); 
        builder = factory.newDocumentBuilder(); 
        
        document = builder.parse(filename); 
        root = document.getDocumentElement();
        List<Change> cambio = new ArrayList();  
        
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
        
        //nodelist = root.getElementsByTagName("Cube"); 
        //nodelist = ((Element)nodelist.item(1)).getElementsByTagName("Cube");
        
        nodelist = root.getElementsByTagName("Film");
        
        if(nodelist!=null && nodelist.getLength() > 0){
            
            System.out.println("Dentro"); 
            
            int numNode = nodelist.getLength(); // numero di elementi contati 
            System.out.println(numNode); 
            
            //element = (Element) nodelist.item(1); // secondo tag
            //String s = element.getAttribute("time");
            
            element = (Element) nodelist.item(0); // dovrebbe prendere solo il primo elemento di 4
            dato = getInfo(element);
            cambio.add(dato);
            
            
            for(int i = 0; i< numNode-1; i++){
                element = (Element) nodelist.item(i);
                dato = getInfo(element);
                cambio.add(dato);
            }
        }
        
        
        return cambio; // array con tutte le cose
         
        */
       
    }
}
