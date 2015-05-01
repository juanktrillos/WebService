/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ac.mongoservice;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import project.ac.mongolib.driven.MongoHandler;
import project.ac.mongolib.file.FileDriver;

/**
 *
 * @author JuanCamilo
 */
@WebService(serviceName = "MongoDB")
public class MongoDB {

    // use @Resource injection to create a WebServiceContext for server logging
    private @Resource
    WebServiceContext webServiceContext;

    /**
     * Web service operation
     *
     * @param className
     * @param data
     * @return
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo ::  add(String, String)">
    @WebMethod(operationName = "add")
    public String add(@WebParam(name = "className") String className,
            @WebParam(name = "data") String data) {

        BasicDBObject obj = create(className, data);
        if (obj != null) {
            try {
                getServletContext().log("add: class = " + className);
                getServletContext().log("add: data  = " + data);
                System.out.println("------------- CARGANDO BASE DE DATOS -------------");
                DBObject fileData = chargeJson();
                MongoHandler mongoHandler = new MongoHandler("service", fileData);
                mongoHandler.insert(obj);
                System.out.println("------------- OBJETO AÑADIDO -------------");
            } catch (UnknownHostException ex) {
                return "Object Coundnt be Inserted";
            }
        } else {
            return "Object Coundnt be Created";
        }
        return "Object Insertion Succed";
    }    //</editor-fold>

    /**
     * Web service operation
     *
     * @param className
     * @param data
     * @return
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: delete(String, String)">
    @WebMethod(operationName = "delete")
    public String delete(@WebParam(name = "className") String className,
            @WebParam(name = "data") String data) {

        BasicDBObject obj = create(className, data);
        if (obj != null) {
            try {
                getServletContext().log("delete: class = " + className);
                getServletContext().log("delete: data  = " + data);
                System.out.println("------------- CARGANDO BASE DE DATOS -------------");
                DBObject fileData = chargeJson();
                MongoHandler mongoHandler = new MongoHandler("service", fileData);
                mongoHandler.remove(obj);
                System.out.println("------------- OBJETO ELIMINADO -------------");
            } catch (UnknownHostException ex) {
                return "Object Coundnt be Removed";
            }
        } else {
            return "Object Coundnt be Created";
        }
        return "Object Removed";
    }//</editor-fold>

    /**
     * Web service operation
     *
     * @param clase
     * @param atribute
     * @param data
     * @return
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: find(String, String, String)">
    @WebMethod(operationName = "find")
    public String find(@WebParam(name = "clase") String clase,
            @WebParam(name = "atribute") String atribute, @WebParam(name = "data") String data) {
        LinkedList<BasicDBObject> r;
        String found = "";

        try {
            BasicDBObject obj = (BasicDBObject) Class.forName(clase).newInstance();
            getServletContext().log("find: class = " + clase);
            getServletContext().log("find: atribute  = " + atribute);
            getServletContext().log("find: data  = " + data);
            System.out.println("------------- CARGANDO BASE DE DATOS -------------");
            DBObject fileData = chargeJson();
            MongoHandler mongoHandler = new MongoHandler("service", fileData);
            r = (LinkedList<BasicDBObject>) mongoHandler.find(obj.getClass(), atribute, data);

            if (r.size() == 0) {
                return "Object not Found or Doesnt Exist";
            } else {
                for (BasicDBObject ob : r) {
                    System.out.println("OBJETO ENCONTRADO: " + ob.toString());
                    found += ob.toString() + "&&";
                }
                System.out.println("------------- OBJETO(S) ENCONTRADO(S) -------------");
                return found;
            }
        } catch (UnknownHostException ex) {
            return "Object Coundnt be Found";
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            return "The Class Coundnt be Charged";
        }
    }//</editor-fold>

    /**
     * Web service operation
     * @param className
     * @return 
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: findAll(String)">
    @WebMethod(operationName = "findAll")
    public String findAll(@WebParam(name = "className") String className) {
        LinkedList<BasicDBObject> rAll;
        String foundAll = "";
        
        try {
            System.out.println("class: "+className);
            BasicDBObject obj = (BasicDBObject) Class.forName(className).newInstance();
            getServletContext().log("findAll: class = " + className);
            System.out.println("------------- CARGANDO BASE DE DATOS -------------");
            DBObject fileData = chargeJson();
            MongoHandler mongoHandler = new MongoHandler("service", fileData);
            rAll = (LinkedList<BasicDBObject>) mongoHandler.findAll(obj.getClass());
            
            if (rAll.size() == 0) {
                return "Objects not Found or Dont Exist";
            } else {
                for (BasicDBObject ob : rAll) {
                    System.out.println("OBJETO ENCONTRADO: " + ob.toString());
                    foundAll += ob.toString() + "&&";
                }
                System.out.println("------------- OBJETO(S) ENCONTRADO(S) -------------");
                return foundAll;
            }
        } catch (UnknownHostException ex) {
            return "Objects Coundnt be Found";
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            return "The Class Coundnt be Charged";
        }
    }
//</editor-fold>

    /**
     * Used for server logging. The operation is oneway: provides no response
     *
     * @param text
     */
    @WebMethod(operationName = "log")
    @Oneway
    public void logServer(@WebParam(name = "message") String text) {
        // log message onto server
        getServletContext().log(text);
    }

    /**
     * Get ServletContext.
     *
     * @return ServletContext object
     */
    private ServletContext getServletContext() {
        return (ServletContext) webServiceContext.getMessageContext().get(
                MessageContext.SERVLET_CONTEXT);
    }

    // Este metodo es tipico de un patron factory.
    // Y sirve para cualquier cosa que herede de BasicDBObject
    // Esa subclase debe tener constructor por defecto. Mandatory!
    /**
     *
     * @param className
     * @param data
     * @return
     */
    //<editor-fold defaultstate="collapsed" desc="private Metodo :: create(String, String)">
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
//</editor-fold>

    /**
     * 
     * @return 
     */
    //<editor-fold defaultstate="collapsed" desc="private Metodo :: chargeJson()">
    private DBObject chargeJson() {
        
        InputStream json = getServletContext().getResourceAsStream("/WEB-INF/db-data.json");
        String cad = "";
        int v;
        try {
            while ((v = json.read()) != -1) {
                char c = (char) v;
                cad += c;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (DBObject) JSON.parse(cad);
    }
//</editor-fold>
}
