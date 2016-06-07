package dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class DaoHibernateGenerico implements DaoGenerico {

    @Override
    public Object save(Object objeto) {
        try {
            Object obj = null;
            HibernateUtil.beginTransaction();
            obj = HibernateUtil.getSession().merge(objeto);
            HibernateUtil.commitTransaction();
            HibernateUtil.closeSession();
            return obj;
        } catch (HibernateException hibernateException) {
            HibernateUtil.rollbackTransaction();
            HibernateUtil.closeSession();
            throw hibernateException;
        }
    }

    @Override
    public void delete(Object objeto) {
        try {
            HibernateUtil.beginTransaction();
            HibernateUtil.getSession().delete(objeto);
            HibernateUtil.commitTransaction();
            HibernateUtil.closeSession();
        } catch (HibernateException hibernateException) {
            HibernateUtil.rollbackTransaction();
            throw new RuntimeException(hibernateException);
        }
    }

    public List list(Class clazz) {
        try {
            List lista = null;
            lista = HibernateUtil.getSession().createCriteria(clazz).list();
            return lista;
        } catch (HibernateException hibernateException) {
            HibernateUtil.rollbackTransaction();
            throw new RuntimeException(hibernateException);
        }
    }

    public Object getById(Serializable id, Class clazz) {
        try {
            Object obj = HibernateUtil.getSession().get(clazz, id);
            return obj;
        } catch (HibernateException hibernateException) {
            HibernateUtil.rollbackTransaction();
            throw new RuntimeException(hibernateException);
        }
    }

    public List listCriterio(Class clazz, String atributo, String criterio) {
        try {
            List lista = null;
            Criteria crit = HibernateUtil.getSession().createCriteria(clazz, atributo);
            crit.add(Restrictions.ilike(atributo, "%" + criterio + "%"));
            crit.addOrder(Order.asc(atributo));
            crit.setMaxResults(30);
            lista = crit.list();
            //HibernateUtil.closeSession();
            return lista;
        } catch (HibernateException hibernateException) {
            HibernateUtil.rollbackTransaction();
            throw new RuntimeException(hibernateException);
        }
    }
}
