package botpubblicita;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author galliFrancesco
 *
 * @brief Prende le informazioni dalla classe "openMap" e le converte in un CSV
 * // Nome utente; id_utente; lat; long; //
 */
public class readCSV {

    String utente, id_utente;
    Long lat, longi;

    public readCSV() {
        utente = "";
        id_utente = "";
        lat = 0L;
        longi = 0L;
    }

    public readCSV(String utente, String id_utente, Long lat, Long longi) {
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
                fw.write(utente +";"+ id_utente + ";" +  lat + ";" + longi + "\n"); 
                
                fw.flush();
                fw.close(); // Devo ricordarmi di chiudere, altrimenti non scrive     
            }
        } else {
            // Registrato nel file
            // TODO: Fare un array che contiene le stringhe con le cose lette dal CSV 
            //              quando poi farà questo metodo, aggiorna il file in base alle nuove conoscenze
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
                System.out.println("Ce l'ho!");
            }
        }
            
        return false;
    }

}
