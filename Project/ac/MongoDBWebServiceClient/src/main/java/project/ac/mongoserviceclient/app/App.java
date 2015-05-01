/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ac.mongoserviceclient.app;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import java.util.Set;
import project.ac.mongoservice.MongoDB;
import project.ac.mongoservice.MongoDB_Service;
import project.ac.mongoserviceclient.data.Persona;

/**
 *
 * @author JuanCamilo
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Persona p = new Persona("Juan Camilo Trillos V.", 20, "Ing. Multimedia.", "2130101");
        String data = p.toString();
        String className = Persona.class.getCanonicalName();

        App app = new App();
        MongoDB port = app.getMongoDBPort();
        port.log("MongoDB STARTED");
        
//        String message = port.add(className, data);
//        port.log(message);
//        System.out.println(message);

//        String delete = port.delete(className, data);
//        port.log(delete);

//        String find = port.find(className, "nombre", "Juan Camilo Trillos V.");
//        String[] found = find.split("&&");
//        for (int i = 0; i < found.length; i++) {
//            System.out.println("enc " + i + ": " + found[i]);
//        }
//        port.log(find);
//        System.out.println(find);

//        String findAll = port.findAll(className);
//        String[] foundAll = findAll.split("&&");
//        for (int i = 0; i < foundAll.length; i++) {
//            System.out.println("enc All " + i + ": " + foundAll[i]);
//        }
//        port.log(findAll);
//        System.out.println(findAll);

        port.log("MongoDB FINISHED");
    }

    /**
     * Get service port stub for Calculator web service.
     */
    private MongoDB getMongoDBPort() {
        MongoDB_Service service = new MongoDB_Service();
        return service.getMongoDBPort();
    }

    // Este metodo es tipico de un patron factory.
    // Y sirve para cualquier cosa que herede de BasicDBObject
    // Esa subclase debe tener constructor por defecto. Mandatory!
    private static BasicDBObject create(String className, String data) {
        BasicDBObject o = null;
        try {
            Class clase = Class.forName(className);
            if (clase != null) {
                o = (BasicDBObject) clase.newInstance();
                if (o != null) {
                    BasicDBObject obj = (BasicDBObject) JSON.parse(data);
                    Set<String> keys = obj.keySet();
                    for (String key : keys) {
                        Object val = obj.get(key);
                        ((BasicDBObject) o).put(key, val);
                    }
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("error create()");
        }

        return o;
    }
}
