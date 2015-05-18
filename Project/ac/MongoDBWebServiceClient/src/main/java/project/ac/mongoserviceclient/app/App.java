/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ac.mongoserviceclient.app;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import java.util.LinkedList;
import java.util.Set;
import project.ac.mongoservice.MongoDB;
import project.ac.mongoservice.MongoDB_Service;

/**
 *
 * @author Olmedo Arcila Guzman - Mentor
 * @author Juan Camilo Trillos Velosa - Ing. Multimedia
 * @author Felipe Garaycochea Lozada - Ing. Multimedia
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
        port.log("MongoDB STARTED");
        port.log("MongoDB FINISHED");
    }

    private void addCollection(String className, String data) {

        String message = port.add(className, data);
        port.log(message);
        System.out.println(message);
    }

    private static void removeCollection(String className, String data) {

        String delete = port.delete(className, data);
        port.log(delete);
        System.out.println(delete);
    }

    private static LinkedList findCollection(String className, String atributo, String data) {

        LinkedList<BasicDBObject> list = new LinkedList<>();
        String find = port.find(className, atributo, data);
        String[] found = find.split("&&");
        for (String f : found) {
            list.add(create(className, f));
        }
        return list;
    }

    private void findAllCollection(String className) {

        String findAll = port.findAll(className);
        String[] foundAll = findAll.split("&&");
        for (int i = 0; i < foundAll.length; i++) {
            port.log("Object " + (i + 1) + ": " + foundAll[i]);
        }
    }

    private void updateCollection(String className, String cClave, String cValor, String aClave, String aValor) {

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
    
    private static BasicDBObject create(String className, String data) {
        BasicDBObject o = null;
        try {
            Class clase = Class.forName(className);
            if (clase != null) {
                o = (BasicDBObject) clase.newInstance();
                if (o != null) {
                    System.out.println(data);
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
