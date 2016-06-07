/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import dao.DaoGenerico;
import dao.DaoHibernateGenerico;
import java.util.List;
import modelo.Cidade;
import modelo.Cliente;
import sun.font.EAttribute;

/**
 *
 * @author Vinicius
 */
public class ClienteControle {
    private DaoGenerico dao;
    private static ClienteControle instance;

    public ClienteControle(){
        dao = new DaoHibernateGenerico();
    }

    public static synchronized ClienteControle getInstance(){
        if(instance == null){
            instance = new ClienteControle();
        }
        return instance;
    }

    public void salvar(Cliente obj){
        dao.save(obj);
    }

    public List<Cidade> listaTodos() {
        return dao.list(Cidade.class);
    }

    public void delete(Cliente cid) {
        dao.delete(cid);
    }

    public List<Cliente> listaFiltrando(String filtro, int tipo) {
         if(tipo == 0){
            return dao.listCriterio(Cliente.class, "nome", filtro);
        } else{
            return dao.listCriterio(Cliente.class, "endereco", filtro);
        }
    }
}
