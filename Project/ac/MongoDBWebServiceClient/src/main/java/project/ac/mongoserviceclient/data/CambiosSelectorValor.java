package project.ac.mongoserviceclient.data;

import com.mongodb.BasicDBObject;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author oarcila
 */
public class CambiosSelectorValor extends BasicDBObject
{

    public static final String idUsuario = "idUsuario";
    public static final String idSelector = "idSelector";
    public static final String date = "date";
    public static final String luz = "luz";
    public static final String valor = "valor";

    private boolean partial;

    public CambiosSelectorValor()
    {
        partial = false;
    }

    public CambiosSelectorValor(String idUsuario, String idSelector, Date date, Boolean luz, Integer valor)
    {
        this.put(CambiosSelectorValor.idUsuario, idUsuario);
        this.put(CambiosSelectorValor.idSelector, idSelector);
        this.put(CambiosSelectorValor.date, date);
        this.put(CambiosSelectorValor.luz, luz);
        this.put(CambiosSelectorValor.valor, valor);

        this.markAsPartialObject();
    }

    @Override
    public final void markAsPartialObject()
    {
        Set<String> set = keySet();
        set.remove("_id");

        Set<String> setThis = new HashSet<>();
        setThis.add(idUsuario);
        setThis.add(idSelector);
        setThis.add(date);
        setThis.add(luz);
        setThis.add(valor);

        partial = !set.equals(setThis);
    }

    @Override
    public boolean isPartialObject()
    {
        return partial;
    }

    public void setIdUsuario(String idUsuario)
    {
        this.put(CambiosSelectorValor.idUsuario, idUsuario);
    }

    public String getIdUsuario()
    {
        return this.getString(CambiosSelectorValor.idUsuario);
    }

    public void setIdSelector(String idSelector)
    {
        this.put(CambiosSelectorValor.idSelector, idSelector);
    }

    public String getIdSelector()
    {
        return this.getString(CambiosSelectorValor.idSelector);
    }

    public void setDate(Date date)
    {
        this.put(CambiosSelectorValor.date, date);
    }

    public Date getDate()
    {
        return (Date) this.get(CambiosSelectorValor.date);
    }

    public void setLuz(Boolean luz)
    {
        this.put(CambiosSelectorValor.luz, luz);
    }

    public Boolean getLuz()
    {
        return this.getBoolean(CambiosSelectorValor.luz);
    }

    public void setValor(Integer valor)
    {
        this.put(CambiosSelectorValor.valor, valor);
    }

    public Integer getValor()
    {
        return this.getInt(CambiosSelectorValor.valor);
    }

//    @Override
//    public String toString()
//    {
//        return getIdUsuario() + " " + getIdSelector() + " " + getDate() + " " + getLuz() + " " + getValor();
//    }

}
