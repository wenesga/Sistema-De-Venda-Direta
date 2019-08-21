package br.com.vendadireta.util;

import org.hibernate.Session;
import org.junit.Test;

/**
 * @Cometario: Classe Teste
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 02/04/2016 - Classe: HiberneteUtilTest
 */
public class HibernateUtilTest {

    @Test
    public void conectar() {
        Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
        sessao.close();
        HibernateUtil.getFabricaDeSessoes().close();
    }
}
