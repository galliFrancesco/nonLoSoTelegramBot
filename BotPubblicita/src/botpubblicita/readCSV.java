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
        this.lat =lat;
        this.longi = longi;
    }

    public void run() throws FileNotFoundException, IOException {

        try (FileWriter fw = new FileWriter("rubrica.csv", true)){ // true attiva la Append mode, ovvero li mette in coda e non li sovrascrive
            fw.write("Ciao; Sono; \n");
            fw.write("Gay; Yay; \n"); 
            
            fw.flush();
            fw.close(); 
            //if(registrato()){}
            // Devo ricordarmi di chiudere, altrimenti non scrive
        }
        
        
        
        
        
        

        BufferedReader br = new BufferedReader(new FileReader("rubrica.csv"));

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

    }

    /**
     * @brief Scorre Il file e vede se l'utente è già registrato
     * @return FALSE -> L'utente non è già registrato
     * @return TRUE -> L'utente è già registrato
     */
    private Boolean registrato() {

        return false;
    }

}
