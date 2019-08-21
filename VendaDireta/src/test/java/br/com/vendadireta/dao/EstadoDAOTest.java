package br.com.vendadireta.dao;

import br.com.vendadireta.entidade.Estado;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 05/04/2016 - Classe: EstadoDAOTest
 */
public class EstadoDAOTest {

    @Test
    @Ignore
    public void salvar() {

        Estado estado = new Estado();
        estado.setNome("Tocatins");
        estado.setSigla("TO");

        EstadoDAO estadoDAO = new EstadoDAO();
        estadoDAO.salvar(estado);
    }

    @Test
    @Ignore
    public void listar() {
        EstadoDAO estadoDAO = new EstadoDAO();
        List<Estado> resultado = estadoDAO.listar();

        for (Estado estado : resultado) {
            System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore
    public void buscar() {
        Integer codigoEstado = 1;
        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(codigoEstado);

        if (estado == null) {
            System.out.println("\nNenhum registro encontrado!\n");
        } else {
            System.out.println("\nRegistro encontrado:");
            System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore
    public void excluir() {
        Integer codigo = 2;
        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(codigo);

        if (estado == null) {
            System.out.println("\nNenhum registro encontrado!\n");
        } else {
            estadoDAO.excluir(estado);
            System.out.println("\nRegistro removido:");
            System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore
    public void editar() {
        Integer codigo = 3;
        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(codigo);

        if (estado == null) {
            System.out.println("\nNenhum registro encontrado!\n");
        } else {
            System.out.println("\nRegistro editado - Antes:");
            System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());

            estado.setNome("Goias");
            estado.setSigla("GO");
            estadoDAO.editar(estado);

            System.out.println("\nRegistro editado - Depois:");
            System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
        }
    }

    @Test
    @Ignore 
    public void merge() {
        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(74);
        estado.setNome("Estado Teste");
        estado.setSigla("TT");
        estadoDAO.merge(estado);
    }

}
