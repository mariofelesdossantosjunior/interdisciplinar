/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import dao.DaoGenerico;
import dao.DaoHibernateGenerico;
import java.util.List;
import modelo.Cidade;
import modelo.Estado;
import sun.font.EAttribute;

/**
 *
 * @author Vinicius
 */
public class CidadeControle {
    private DaoGenerico dao;
    private static CidadeControle instance;

    public CidadeControle(){
        dao = new DaoHibernateGenerico();
    }

    public static synchronized CidadeControle getInstance(){
        if(instance == null){
            instance = new CidadeControle();
        }
        return instance;
    }

    public void salvar(Cidade obj){
        dao.save(obj);
    }

    public List<Estado> listaTodos() {
        return dao.list(Estado.class);
    }

    public void delete(Cidade cid) {
        dao.delete(cid);
    }

    public List<Cidade> listaFiltrando(String filtro, int tipo) {
            return dao.listCriterio(Cidade.class, "nome", filtro);
    }
}
