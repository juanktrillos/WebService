package project.uni.artefacto;

import project.uni.artefacto.data.Selector;
import project.uni.artefacto.data.Dispositivo;
import project.uni.artefacto.data.RelacionDispositivoSelector;
import project.uni.artefacto.data.SelectorValor;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.ac.mongolib.driven.MongoHandler;

/**
 *
 * @author oarcila
 */
public class Principal
{

    public static void main(String[] args)
    {
        System.out.println("-- Dispositivos: Java Mongo Maven --");

        Dispositivo dispositivo = new Dispositivo("Encencedor-Sala-Principal",Boolean.FALSE);
//        dispositivo.add(new Selector("Selector-Bombillo-Sala-Uno", Boolean.FALSE));
//        dispositivo.add(new Selector("Selector-Bombillo-Sala-Dos", Boolean.FALSE));
//        dispositivo.add(new Selector("Selector-Bombillo-Sala-Tres", Boolean.TRUE));
        System.out.println(dispositivo.toString());

        dispositivo = new Dispositivo("Encencedor-Alcoba-Principal", Boolean.FALSE);
//        dispositivo.add(new Selector("Selector-Bombillo-Alcoba-Uno", Boolean.FALSE));
        System.out.println(dispositivo.toString());
//        try
//        {
//            MongoHandler mongoHandler = new MongoHandler("Artefactos");
            
//              Selectores
//            mongoHandler.insert(new Selector("Selector-Bombillo-Sala-Uno", Boolean.FALSE));
//            mongoHandler.insert(new Selector("Selector-Bombillo-Sala-Dos", Boolean.FALSE));
//            mongoHandler.insert(new Selector("Selector-Bombillo-Sala-Tres", Boolean.FALSE));
//            mongoHandler.insert(new Selector("Selector-Bombillo-Alcoba-Uno", Boolean.FALSE));
//              Dispositivos
//            mongoHandler.insert(new Dispositivo("Encencedor-Sala-Principal",Boolean.FALSE));
//            mongoHandler.insert(new Dispositivo("Encencedor-Alcoba-Principal",Boolean.FALSE));
//              Valores Selectores
//            mongoHandler.insert(new SelectorValor("54b7d82a98d5e5d4ccd42902", Boolean.FALSE, 100));
//            mongoHandler.insert(new SelectorValor("54b7d82a98d5e5d4ccd42903", Boolean.FALSE, 100));
//            mongoHandler.insert(new SelectorValor("54b7d82a98d5e5d4ccd42904", Boolean.FALSE, 100));
//            mongoHandler.insert(new SelectorValor("54b7d82a98d5e5d4ccd42905", Boolean.FALSE, 100));
//              Relacion Entre Dispositivos y Selectores
//            mongoHandler.insert(new RelacionDispositivoSelector("54b7dc7898d5755ed3a4ae8d", "54b7d82a98d5e5d4ccd42902"));
//            mongoHandler.insert(new RelacionDispositivoSelector("54b7dc7898d5755ed3a4ae8d", "54b7d82a98d5e5d4ccd42903"));
//            mongoHandler.insert(new RelacionDispositivoSelector("54b7dc7898d5755ed3a4ae8d", "54b7d82a98d5e5d4ccd42904"));
//            mongoHandler.insert(new RelacionDispositivoSelector("54b7dc7998d5755ed3a4ae8e", "54b7d82a98d5e5d4ccd42905"));
            
//            System.out.println("Selectores");
//            LinkedList<Selector> selectores = (LinkedList<Selector>) mongoHandler.findAll(Selector.class);
//            selectores.stream().forEach((selector) ->
//            {
//                System.out.println(selector.toString());
//            });
//
//            System.out.println("Dispositivos");
//            LinkedList<Dispositivo> dispositivos = (LinkedList<Dispositivo>) mongoHandler.findAll(Dispositivo.class);
//            dispositivos.stream().forEach((dispositivo) ->
//            {
//                System.out.println(dispositivo.toString());
//            });
//
//            System.out.println("Relacion Dispositivos - Selectores");
//            LinkedList<RelacionDispositivoSelector> lRelDispSel = (LinkedList<RelacionDispositivoSelector>) mongoHandler.findAll(RelacionDispositivoSelector.class);
//            lRelDispSel.stream().forEach((relDispSel) ->
//            {
//                System.out.println(relDispSel.toString());
//            });
//
//            System.out.println("Valor Selectores");
//            LinkedList<SelectorValor> selectoresValor = (LinkedList<SelectorValor>) mongoHandler.findAll(SelectorValor.class);
//            selectoresValor.stream().forEach((selectorValor) ->
//            {
//                System.out.println(selectorValor.toString());
//            });
//
//        }
//        catch (UnknownHostException ex)
//        {
//            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
