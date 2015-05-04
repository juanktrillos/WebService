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
import project.ac.mongolib.driven.CriterioActualizacion;
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
                System.out.println("------------- AÑADIENDO A LA BASE DE DATOS -------------");
                getServletContext().log("add: class = " + className);
                getServletContext().log("add: data  = " + data);
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
                System.out.println("------------- REMOVIENDO DE LA BASE DE DATOS -------------");
                getServletContext().log("delete: class = " + className);
                getServletContext().log("delete: data  = " + data);
                DBObject fileData = chargeJson();
                MongoHandler mongoHandler = new MongoHandler("service", fileData);
                mongoHandler.remove(obj);
                System.out.println("------------- OBJETO REMOVIDO -------------");
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
     * @param className
     * @param atribute
     * @param data
     * @return
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: find(String, String, String)">
    @WebMethod(operationName = "find")
    public String find(@WebParam(name = "className") String className, @WebParam(name = "atribute") String atribute,
            @WebParam(name = "data") String data) {
        LinkedList<BasicDBObject> r;
        String found = "";

        try {
            System.out.println("------------- BUSCANDO EN LA BASE DE DATOS -------------");
            BasicDBObject obj = (BasicDBObject) Class.forName(className).newInstance();
            getServletContext().log("find: class = " + className);
            getServletContext().log("find: atribute  = " + atribute);
            getServletContext().log("find: data  = " + data);
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
     *
     * @param className
     * @return
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: findAll(String)">
    @WebMethod(operationName = "findAll")
    public String findAll(@WebParam(name = "className") String className) {
        LinkedList<BasicDBObject> rAll;
        String foundAll = "";

        try {
            System.out.println("------------- BUSCANDO EN LA BASE DE DATOS -------------");
            System.out.println("class: " + className);
            BasicDBObject obj = (BasicDBObject) Class.forName(className).newInstance();
            getServletContext().log("findAll: class = " + className);
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
     * Web service operation
     *
     * @param className
     * @param criterioClave
     * @param criterioValor
     * @param atributoClave
     * @param atributoValor
     * @return
     */
    //<editor-fold defaultstate="collapsed" desc="Metodo :: update(String, String, String, String, String)">
    @WebMethod(operationName = "update")
    public String update(@WebParam(name = "className") String className,
            @WebParam(name = "criterioClave") String criterioClave, @WebParam(name = "criterioValor") String criterioValor,
            @WebParam(name = "atributoClave") String atributoClave, @WebParam(name = "atributoValor") String atributoValor) {

        try {
            System.out.println("------------- MODIFICANDO LA BASE DE DATOS -------------");
            getServletContext().log("update: class = " + className);
            getServletContext().log("update: criterioClave = " + criterioClave);
            getServletContext().log("update: criterioValor = " + criterioValor);
            getServletContext().log("update: atributoClave = " + atributoClave);
            getServletContext().log("update: atributoValor = " + atributoValor);
            BasicDBObject obj = (BasicDBObject) Class.forName(className).newInstance();
            CriterioActualizacion cAct = new CriterioActualizacion();
            cAct.setCriterio(criterioClave, criterioValor);
            cAct.setNuevoValor(atributoClave, atributoValor);

            DBObject fileData = chargeJson();
            MongoHandler mongoHandler = new MongoHandler("service", fileData);
            mongoHandler.update(obj.getClass(), cAct);
            System.out.println("------------- BASE DE DATOS MODIFICADA-------------");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnknownHostException ex) {
            return "There was a Mistake or a Error, Try Again";
        }
        return "Atribute was Updated";
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
