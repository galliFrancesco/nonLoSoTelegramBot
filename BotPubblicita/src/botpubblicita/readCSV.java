package botpubblicita;

import TelegramAPI.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author galliFrancesco
 *
 * @brief Prende le informazioni dalla classe "openMap" e le converte in un CSV
 * // Nome utente; id_utente; lat; long; //
 */

public class readCSV {

    List<String> contatti; 
    String utente;
    Long id_utente;
    String lat, longi;
    
    Test t = new Test(); 

    public readCSV() {
        utente = "";
        id_utente = 0L;
        lat = "";
        longi = "";
    }

    public readCSV(String utente, Long id_utente, String lat, String longi) {
        this.utente = utente;
        this.id_utente = id_utente;
        this.lat = lat;
        this.longi = longi;
    }

    public void run() throws FileNotFoundException, IOException {

        if (!registrato()) {
            // Non registrato nel file CSV
            try ( FileWriter fw = new FileWriter("rubrica.csv", true)) { // true attiva la Append mode, ovvero li mette in coda e non li sovrascrive
                // Nome utente; id_utente; lat; long; //
                fw.append(utente +";"+ id_utente + ";" +  lat + ";" + longi + "\n"); 
                System.out.println(longi); 
                fw.flush();
                fw.close(); // Devo ricordarmi di chiudere, altrimenti non scrive     
            }
            
            // Aggiunte le tue info
            
            t.sendMessage("sendMessage?chat_id=" + id_utente + "&text=Aggiunte+le+tue+info!");
            
        } else {
            // Registrato nel file
            // TODO: Fare un array che contiene le stringhe con le cose lette dal CSV X
            //              quando poi farà questo metodo, aggiorna il file in base alle nuove conoscenze
            
            leggiRubrica(); 
            
            // Lette tutto l'array
            for(int i = 0; i<contatti.size(); i++){
                // Se l'ID è uguale a quello che ho ora
                
                String linT = contatti.get(i).substring(contatti.get(i).indexOf(";")+1);// substring del primo valore
                String id_utRub = linT.substring(0, linT.indexOf(";")); 
                
                if(id_utRub.equals(id_utente)){ // Se l'ID è uguale a quello che sto cercando 
                    
                    String mod = utente + ";" + id_utente +  ";" + lat + ";" + longi; 
                    
                    contatti.set(i, mod);
                }
                // E questo modifica il record in locale
                System.out.println(contatti.get(i)); 
            }
            
            // Ora va salvato sul CSV
            try (FileWriter fw = new FileWriter("rubrica.csv")) {
                // Ora va salvato sul CSV
                for(int i = 0; i<contatti.size(); i++){
                    fw.append(contatti.get(i));
                    
                }
                fw.flush();
                fw.close();
            } 
            
            //System.out.println("Aggiornato!");
        }

    }

    /**
     * @brief Scorre Il file e vede se l'utente è già registrato
     * @return FALSE -> L'utente non è già registrato
     * @return TRUE -> L'utente è già registrato
     */
    public Boolean registrato() throws FileNotFoundException, IOException {
        // Lettura file CSV
        // Nome utente; id_utente; lat; long; //
        BufferedReader br = new BufferedReader(new FileReader("rubrica.csv")); // e leggo il file
        
        String linea; 
        Boolean sent = false;  
        
        while((linea=br.readLine()) != null && !sent){
            //substring
            String linT = linea.substring(linea.indexOf(";")+1);// substring del primo valore
            String linT2 = linT.substring(0, linT.indexOf(";"));
            
            
            if(linT2.equals(id_utente)) {// se il codice utente è già contenuto // FUNZIA :D
                sent = true; 
                //System.out.println("Ce l'ho!");
            }
        }
            
        return sent;
    }
    
    /**
     * @brief Legge tutti i valori della rubrica e lo salva in un Array 
     *
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void leggiRubrica() throws FileNotFoundException, IOException{
        // Inizializzo un nuovo arrayList
        contatti = new ArrayList();
        
        BufferedReader br = new BufferedReader(new FileReader("rubrica.csv")); // e leggo il file
        
        String linea; 
        
        while((linea=br.readLine()) != null ){
            contatti.add(linea); 
        }
        
        // Lettura di tutto l'arrayList
        //for(int i = 0; i<contatti.size(); i++){
            //System.out.println(contatti.get(i));
        //}
    }

}
