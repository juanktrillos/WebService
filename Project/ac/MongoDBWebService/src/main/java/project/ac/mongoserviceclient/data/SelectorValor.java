package project.ac.mongoserviceclient.data;

import com.mongodb.BasicDBObject;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author oarcila
 */
public class SelectorValor extends BasicDBObject {

    public static final String idSelector = "idSelector";
    public static final String luz = "luz";
    public static final String valor = "valor";

    private boolean partial;

    public SelectorValor() {
        partial = false;
    }

    public SelectorValor(String idSelector, Boolean luz, Integer valor) {
        this.put(SelectorValor.idSelector, idSelector);
        this.put(SelectorValor.luz, luz);
        this.put(SelectorValor.valor, valor);

        this.markAsPartialObject();
    }

    @Override
    public final void markAsPartialObject() {
        Set<String> set = keySet();
        set.remove("_id");

        Set<String> setThis = new HashSet<>();
        setThis.add(idSelector);
        setThis.add(luz);
        setThis.add(valor);

        partial = !set.equals(setThis);
    }

    @Override
    public boolean isPartialObject() {
        return partial;
    }

    public void setIdSelector(String idSelector) {
        this.put(SelectorValor.idSelector, idSelector);
    }

    public String getIdSelector() {
        return this.getString(SelectorValor.idSelector);
    }

    public void setLuz(Boolean luz) {
        this.put(SelectorValor.luz, luz);
    }

    public Boolean getLuz() {
        this.put(SelectorValor.luz, Boolean.valueOf(get(SelectorValor.luz).toString()));
        return this.getBoolean(SelectorValor.luz);
    }

    public void setValor(Integer valor) {
        this.put(SelectorValor.valor, valor);
    }

    public Integer getValor() {
        this.put(SelectorValor.valor, Integer.valueOf(get(SelectorValor.valor).toString()));
        return this.getInt(SelectorValor.valor);
    }
//
//    @Override
//    public String toString()
//    {
//        return getIdSelector() + " " + getLuz() + " " + getValor();
//    }

}
