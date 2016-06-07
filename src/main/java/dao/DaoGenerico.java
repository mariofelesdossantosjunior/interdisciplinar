package dao;

import java.io.Serializable;
import java.util.List;

public interface DaoGenerico {

    public Object save(Object objeto);

    public void delete(Object objeto);

    public List list(Class clazz);

    public List listCriterio(Class clazz, String atributo, String criterio);

    public Object getById(Serializable id, Class clazz);
}
