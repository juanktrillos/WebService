package project.uni.artefacto.data;

import com.mongodb.BasicDBObject;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author oarcila
 */
public class Selector extends BasicDBObject
{

    public static final String nombre = "nombre";
    public static final String activo = "activo";

    private boolean partial;

    public Selector()
    {
        partial = false;
    }

    public Selector(String nombre, Boolean activo)
    {
        this.put(Selector.nombre, nombre);
        this.put(Selector.activo, activo);

        this.markAsPartialObject();
    }

    @Override
    public final void markAsPartialObject()
    {
        Set<String> set = keySet();
        set.remove("_id");

        Set<String> setThis = new HashSet<>();
        setThis.add(nombre);
        setThis.add(activo);

        partial = !set.equals(setThis);
    }

    @Override
    public boolean isPartialObject()
    {
        return partial;
    }

    public void setNombre(String nombre)
    {
        this.put(Selector.nombre, nombre);
    }

    public String getNombre()
    {
        return this.getString(Selector.nombre);
    }

    public void setActivo(String activo)
    {
        this.put(Selector.activo, activo);
    }

    public Boolean getActivo()
    {
        return this.getBoolean(Selector.activo);
    }

    @Override
    public String toString()
    {
        return get("_id") + " " + getNombre() + " " + getActivo();
    }
}
