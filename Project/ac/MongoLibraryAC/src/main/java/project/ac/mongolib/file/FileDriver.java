/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ac.mongolib.file;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class FileDriver {

    public static DBObject leerArchivo(String nomArch) {
        String cad_json = "";
        try (BufferedReader br = new BufferedReader(new FileReader(nomArch))) {
            String line;
            while ((line = br.readLine()) != null) {
                cad_json += line;
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        return (DBObject) JSON.parse(cad_json);
    }

    public static DBObject leerArchivo(InputStream json) {

        String cad = "";
        int v;
        try {
            while ((v = json.read()) != -1) {
                char c = (char) v;
                cad += c;
            }
            System.out.println(cad);
        } catch (IOException ex) {
            Logger.getLogger(FileDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (DBObject) JSON.parse(cad);
    }
}
