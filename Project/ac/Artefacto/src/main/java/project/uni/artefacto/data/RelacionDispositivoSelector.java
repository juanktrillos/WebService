package project.uni.artefacto.data;

import com.mongodb.BasicDBObject;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author oarcila
 */
public class RelacionDispositivoSelector extends BasicDBObject
{
    public static final String idDispositivo = "idDispositivo";
    public static final String idSelector = "idSelector";
    
    private boolean partial;
    
    public RelacionDispositivoSelector()
    {
        partial = false;
    }

    public RelacionDispositivoSelector(String idDispositivo, String idSelector)
    {
        this.put(RelacionDispositivoSelector.idDispositivo, idDispositivo);
        this.put(RelacionDispositivoSelector.idSelector, idSelector);

        this.markAsPartialObject();
    }

    @Override
    public final void markAsPartialObject()
    {
        Set<String> set = keySet();
        set.remove("_id");

        Set<String> setThis = new HashSet<>();
        setThis.add(idSelector);
        setThis.add(idDispositivo);

        partial = !set.equals(setThis);
    }

    @Override
    public boolean isPartialObject()
    {
        return partial;
    }

    public void setIdDispositivo(String idSelector)
    {
        this.put(RelacionDispositivoSelector.idDispositivo, idDispositivo);
    }

    public String getIdDispositivo()
    {
        return this.getString(RelacionDispositivoSelector.idDispositivo);
    }

    public void setIdSelector(String idSelector)
    {
        this.put(RelacionDispositivoSelector.idSelector, idSelector);
    }

    public String getIdSelector()
    {
        return this.getString(RelacionDispositivoSelector.idSelector);
    }
    
    @Override
    public String toString()
    {
        return getIdDispositivo() + " " + getIdSelector();
    }

}
