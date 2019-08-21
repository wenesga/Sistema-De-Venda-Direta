package br.com.vendadireta.dao;

import br.com.vendadireta.entidade.Local;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @Cometario:  
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date   02/05/2016 - Classe: LocalDAOTest
 */
public class LocalDAOTest {

    @Test
    @Ignore
    public void salvar(){
        Local local = new Local();
        local.setDescricao("Marcador Teste");
        local.setLatitude(new BigDecimal("-12.9272021"));
        local.setLongitude(new BigDecimal("-12.9272021"));
        
        LocalDAO localDAO = new LocalDAO();
        localDAO.salvar(local);
    }
    
    @Test
    @Ignore
    public void listar() {
        LocalDAO localDAO = new LocalDAO();
        List<Local> resultado = localDAO.listar();

        for (Local local : resultado) {
            System.out.println("CODIGO = " + local.getCodigo());
            System.out.println("NOME = " + local.getDescricao());
            System.out.println("LATITUDE = " + local.getLatitude());
            System.out.println("LOGITUDE = " + local.getLongitude());
        }
    }
    
    @Test
    @Ignore
    public void buscar() {
        Integer codigoLocal = 1;
        LocalDAO localDAO = new LocalDAO();
        Local local = localDAO.buscar(codigoLocal);
        

        if (local == null) {
            System.out.println("\nNenhum registro encontrado!\n");
        } else {
            System.out.println("\nRegistro encontrado:");
            System.out.println("CODIGO = " + local.getCodigo());
            System.out.println("NOME = " + local.getDescricao());
            System.out.println("LATITUDE = " + local.getLatitude());
            System.out.println("LOGITUDE = " + local.getLongitude());
        }
    }
    
    @Test
    @Ignore
    public void excluir() {
        Integer codigoLocal = 2;
        LocalDAO localDAO = new LocalDAO();
        Local local = localDAO.buscar(codigoLocal);

        if (local == null) {
            System.out.println("\nNenhum registro encontrado!\n");
        } else {
            localDAO.excluir(local);
            System.out.println("\nRegistro removido:");
            System.out.println("CODIGO = " + local.getCodigo());
            System.out.println("NOME = " + local.getDescricao());
            System.out.println("LATITUDE = " + local.getLatitude());
            System.out.println("LOGITUDE = " + local.getLongitude());
        }
    }
    
    
    @Test
    @Ignore
    public void editar() {
        Integer codigoLocal = 3;
        LocalDAO localDAO = new LocalDAO();
        Local local = localDAO.buscar(codigoLocal);

        if (local == null) {
            System.out.println("\nNenhum registro encontrado!\n");
        } else {
            System.out.println("\nRegistro editado - Antes:");
            System.out.println("CODIGO = " + local.getCodigo());
            System.out.println("NOME = " + local.getDescricao());
            System.out.println("LATITUDE = " + local.getLatitude());
            System.out.println("LOGITUDE = " + local.getLongitude());
            
            local.setDescricao("Goias");
            local.setLatitude(new BigDecimal("-12.9272021"));
            local.setLongitude(new BigDecimal("-12.9272021"));
            localDAO.editar(local);

            System.out.println("\nRegistro editado - Depois:");
            System.out.println("CODIGO = " + local.getCodigo());
            System.out.println("NOME = " + local.getDescricao());
            System.out.println("LATITUDE = " + local.getLatitude());
            System.out.println("LOGITUDE = " + local.getLongitude());
        }
    }
    
    
    @Test
    @Ignore 
    public void merge() {
        
        LocalDAO localDAO = new LocalDAO();
        Local local = localDAO.buscar(3);
        
        local.setDescricao("Arraias");
        local.setLatitude(new BigDecimal("-12.9272021"));
        local.setLongitude(new BigDecimal("-46.9450947"));
        localDAO.merge(local);
    }
}
