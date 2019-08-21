package br.com.vendadireta.dao;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.vendadireta.entidade.Usuario;
import br.com.vendadireta.util.HibernateUtil;
/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 09/04/2016 - Classe: UsuarioDAO
 */
@SuppressWarnings("unused")
public class UsuarioDAO extends GenericDAO<Usuario> {
    
    public Usuario autenticar(String cpf, String senha){
        Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
        
        try{
            Criteria consulta = sessao.createCriteria(Usuario.class);         
            consulta.add(Restrictions.eq("cpf", cpf));
            //SimpleHash hash =new SimpleHash("md5", senha);
            consulta.add(Restrictions.eq("senha", senha));
            
            Usuario resultado = (Usuario) consulta.uniqueResult();
            
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }
}
