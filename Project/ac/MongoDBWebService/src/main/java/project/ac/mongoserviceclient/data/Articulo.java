package project.ac.mongoserviceclient.data;

import com.mongodb.BasicDBObject;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author olarguz
 */
public class Articulo extends BasicDBObject
{
    public static String NOMBRE = "nombre";
    public static String REFERENCIA = "referencia";
    public static String MODELO = "modelo";

    private boolean partial;
    
    public Articulo ()
    {
        partial = false;
    }
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Articulo (String nombre, String referencia, int Modelo)
    {
        this.put(Articulo.NOMBRE, nombre);
        this.put(Articulo.REFERENCIA, referencia);
        this.put(Articulo.MODELO, Modelo);
        
        this.markAsPartialObject();
    }
    
    @Override
    public void markAsPartialObject() {
        Set<String> set = keySet();
        set.remove("_id");
        
        Set<String> setThis = new HashSet<>();
        setThis.add(NOMBRE);
        setThis.add(REFERENCIA);
        setThis.add(MODELO);
        
        partial =  !set.equals(setThis);
    }
    
    public String getNombre ()
    {
        return this.getString(Articulo.NOMBRE);
    }

    @Override
    public boolean isPartialObject() {
        return partial;
    }
}
