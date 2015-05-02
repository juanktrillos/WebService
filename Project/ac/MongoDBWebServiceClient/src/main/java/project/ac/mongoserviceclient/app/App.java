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

    private static MongoDB port;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        App app = new App();
        port = app.getMongoDBPort();

//        Persona p = new Persona("TRILLOS", 20, "ING", "2130101");
//        String className = Persona.class.getCanonicalName();

        port.log("MongoDB STARTED");

        //ADD OBJECT
//        addCollection(className, p.toString());

        //REMOVE OBJECT
//        removeCollection(className, p.toString());

        //FIND OBJECT(S)
//        findCollection(className, "nombre", "camilo");

        //FIND ALL OBJECTS
//        findAllCollection(className);

        //UPDATE ATRIBUTE OBJECT
//        updateCollection(className, Persona.NOMBRE, "Juan Camilo T", Persona.NOMBRE, "camilo");

        port.log("MongoDB FINISHED");
    }

    private static void addCollection(String className, String data) {

        String message = port.add(className, data);
        port.log(message);
        System.out.println(message);
    }

    private static void removeCollection(String className, String data) {

        String delete = port.delete(className, data);
        port.log(delete);
        System.out.println(delete);
    }

    private static void findCollection(String className, String atributo, String data) {

        String find = port.find(className, atributo, data);
        String[] found = find.split("&&");
        for (int i = 0; i < found.length; i++) {
            System.out.println("Object " + (i + 1) + ": " + found[i]);
            port.log("Object " + (i + 1) + ": " + found[i]);
        }
    }

    private static void findAllCollection(String className) {

        String findAll = port.findAll(className);
        String[] foundAll = findAll.split("&&");
        for (int i = 0; i < foundAll.length; i++) {
            System.out.println("Object " + (i + 1) + ": " + foundAll[i]);
            port.log("Object " + (i + 1) + ": " + foundAll[i]);
        }
    }

    private static void updateCollection(String className, String cClave, String cValor, String aClave, String aValor) {

        String update = port.update(className, cClave, cValor, aClave, aValor);
        port.log(update);
        System.out.println(update);
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
