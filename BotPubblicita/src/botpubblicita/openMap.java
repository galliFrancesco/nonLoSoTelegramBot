package botpubblicita;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

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
    
    public void run(String azione) throws MalformedURLException, IOException{
        
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
        
        /*
        XMLOperations xmlo = new XMLOperations(); 

        // prende il link del file XML
        //URL url = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
        URL url = new URL("http://localhost/galli/Boh%20tecnologia/?METHOD_=GET&Tabella=film");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        // legge tutto il file
        String line;  
        String docFinale = ""; 
        while((line = in.readLine())!= null){ // finchè legge righe 
            //System.out.println(line);
            docFinale += line + "\n"; // viene un file XML su tutta la riga
        }
        
        // Salvo la linea di prima in un file XML locale 
        FileWriter fw = new FileWriter("AIUTO.xml"); 
        fw.write(docFinale); 
        
        fw.close(); 
              
        // Questo lo converte in Document, non so se mi serve effettivamente
        Document doc = convertStringToXMLDocument(docFinale);
        System.out.println(doc.getFirstChild().getNodeName());
        
        xmlo.parseDocument("AIUTO.xml"); 
        */
    }
}
