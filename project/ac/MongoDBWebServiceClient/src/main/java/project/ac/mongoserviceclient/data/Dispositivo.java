package project.ac.mongoserviceclient.data;

import com.mongodb.BasicDBObject;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author oarcila
 */
public class Dispositivo extends BasicDBObject {

    public static final String nombre = "nombre";
    public static final String activo = "activo";

    private boolean partial;

    public Dispositivo() {
        partial = false;
    }

    public Dispositivo(String nombre, Boolean activo) {
        this.put(Dispositivo.nombre, nombre);
        this.put(Dispositivo.activo, activo);

        this.markAsPartialObject();
    }

    @Override
    public final void markAsPartialObject() {
        Set<String> set = keySet();
        set.remove("_id");

        Set<String> setThis = new HashSet<>();
        setThis.add(nombre);
        setThis.add(activo);

        partial = !set.equals(setThis);
    }

    @Override
    public boolean isPartialObject() {
        return partial;
    }

    public void setNombre(String nombre) {
        if (this.getNombre().isEmpty()) {
            this.put(Dispositivo.nombre, nombre);
        }
    }

    public String getNombre() {
        return this.getString(Dispositivo.nombre);
    }

    public void setActivo(String activo) {
        this.put(Dispositivo.activo, activo);
    }

    public Boolean getActivo() {
        this.put(Dispositivo.activo, Boolean.valueOf(get(Dispositivo.activo).toString()));
        return this.getBoolean(Dispositivo.activo);
    }

//    @Override
//    public String toString()
//    {
//        return get("_id") + " " + getNombre() + " " + getActivo();
//    }
}
