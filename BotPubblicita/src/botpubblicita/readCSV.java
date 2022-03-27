package botpubblicita;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

    public void run() throws FileNotFoundException, IOException {

        PrintWriter writer = new PrintWriter("test.csv");
        //writer.append("ciao;sono"); // <- Funziona 

        //if(registrato()){}
        // Devo ricordarmi di chiudere, altrimenti non scrive
        writer.close();

        BufferedReader br = new BufferedReader(new FileReader("test.csv"));

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
