package br.com.vendadireta.dao;

import br.com.vendadireta.entidade.Cidade;
import br.com.vendadireta.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
/**
 * @Cometario: Classe cidade
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 07/04/2016 - Classe: CidadeDAO
 */
public class CidadeDAO extends GenericDAO<Cidade> {
    
    @SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Integer estadoCodigo){
        Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

        try {
            Criteria consulta = sessao.createCriteria(Cidade.class);
            consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
            
            consulta.addOrder(Order.asc("nome"));
            List<Cidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }
}
