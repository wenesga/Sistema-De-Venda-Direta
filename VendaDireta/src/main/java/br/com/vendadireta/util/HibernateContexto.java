package br.com.vendadireta.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Cometario: Performace do carregamento do Apache Tomcat e Hibernate
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 16/04/2016 - Classe: HibernateContexto
 */
public class HibernateContexto implements ServletContextListener {

    //Quando inicia o Tomcat
    @Override
    public void contextInitialized(ServletContextEvent event) {
        HibernateUtil.getFabricaDeSessoes().openSession();
    }

    //Quando desliga o Tomcat
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        HibernateUtil.getFabricaDeSessoes().close();
    }

}
