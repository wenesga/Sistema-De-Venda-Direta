package br.com.vendadireta.dao;

import br.com.vendadireta.entidade.Cidade;
import br.com.vendadireta.entidade.Estado;
import java.util.List;
import org.junit.Test;
import org.junit.Ignore;

/**
 * @Cometario: Classe de teste DAO
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 07/04/2016 - Classe: CidadeDAOTest
 */
public class CidadeDAOTest {

    @Test
    @Ignore
    public void salvar() {
        Integer codigoEstado = 2;

        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(codigoEstado);

        Cidade cidade = new Cidade();
        cidade.setNome("Arraias");
        cidade.setEstado(estado);

        CidadeDAO cidadeDAO = new CidadeDAO();
        cidadeDAO.salvar(cidade);
    }

    @Test
    @Ignore
    public void listar() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        List<Cidade> resultado = cidadeDAO.listar();

        for (Cidade cidade : resultado) {
            System.out.println();
            System.out.println("Codigo da cidade: " + cidade.getCodigo());
            System.out.println("Nome da cidade: " + cidade.getNome());
            System.out.println("Codigo do estado: " + cidade.getEstado().getCodigo());
            System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
            System.out.println("Nome do estado: " + cidade.getEstado().getNome());
            System.out.println();
        }
    }

    @Test
    @Ignore
    public void buscar() {
        Integer codigo = 3;

        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.buscar(codigo);

        System.out.println();
        System.out.println("Codigo da cidade: " + cidade.getCodigo());
        System.out.println("Nome da cidade: " + cidade.getNome());
        System.out.println("Codigo do estado: " + cidade.getEstado().getCodigo());
        System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
        System.out.println("Nome do estado: " + cidade.getEstado().getNome());
        System.out.println();
    }

    @Test
    @Ignore
    public void excluir() {
        Integer codigo = 5;

        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.buscar(codigo);

        cidadeDAO.excluir(cidade);

        System.out.println();
        System.out.println("Cidada removida:");
        System.out.println("Codigo da cidade: " + cidade.getCodigo());
        System.out.println("Nome da cidade: " + cidade.getNome());
        System.out.println("Codigo do estado: " + cidade.getEstado().getCodigo());
        System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
        System.out.println("Nome do estado: " + cidade.getEstado().getNome());
        System.out.println();
    }

    @Test
    @Ignore
    public void editar() {
        Integer codigo = 2;

        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.buscar(codigo);

        System.out.println();
        System.out.println("Cidada a ser editada:");
        System.out.println("Codigo da cidade: " + cidade.getCodigo());
        System.out.println("Nome da cidade: " + cidade.getNome());
        System.out.println("Codigo do estado: " + cidade.getEstado().getCodigo());
        System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
        System.out.println("Nome do estado: " + cidade.getEstado().getNome());
        System.out.println();

        cidade.setNome("Nova Arraias");

        cidadeDAO.editar(cidade);

        System.out.println();
        System.out.println("Cidada editada:");
        System.out.println("Codigo da cidade: " + cidade.getCodigo());
        System.out.println("Nome da cidade: " + cidade.getNome());
        System.out.println("Codigo do estado: " + cidade.getEstado().getCodigo());
        System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
        System.out.println("Nome do estado: " + cidade.getEstado().getNome());
        System.out.println();

    }

    @Test
    @Ignore
    public void editarChaveEstrangeira() {
        Integer codigoCidade = 7;
        Integer codigoEstado = 4;

        EstadoDAO estadoDAO = new EstadoDAO();
        Estado estado = estadoDAO.buscar(codigoEstado);

        System.out.println();
        System.out.println("Nome do estado: " + estado.getNome());
        System.out.println("Sigla do estado: " + estado.getSigla());
        System.out.println("Codigo do estado: " + estado.getCodigo());

        System.out.println();

        CidadeDAO cidadeDAO = new CidadeDAO();
        Cidade cidade = cidadeDAO.buscar(codigoCidade);

        System.out.println();
        System.out.println("Cidada a ser editada:");
        System.out.println("Codigo da cidade: " + cidade.getCodigo());
        System.out.println("Nome da cidade: " + cidade.getNome());
        System.out.println("Codigo do estado: " + cidade.getEstado().getCodigo());
        System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
        System.out.println("Nome do estado: " + cidade.getEstado().getNome());
        System.out.println();

        cidade.setNome("Palmas");
        cidade.setEstado(estado);

        cidadeDAO.editar(cidade);

        System.out.println();
        System.out.println("Cidada editada:");
        System.out.println("Codigo da cidade: " + cidade.getCodigo());
        System.out.println("Nome da cidade: " + cidade.getNome());
        System.out.println("Codigo do estado: " + cidade.getEstado().getCodigo());
        System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
        System.out.println("Nome do estado: " + cidade.getEstado().getNome());
        System.out.println();

    }

    @Test
    @Ignore
    public void buscarPorEstado() {
        
        Integer estadoCodigo = 27;
        
        CidadeDAO cidadeDAO = new CidadeDAO();
        List<Cidade> resultado = cidadeDAO.buscarPorEstado(estadoCodigo);

        for (Cidade cidade : resultado) {
            System.out.println();
            System.out.println("Codigo da cidade: " + cidade.getCodigo());
            System.out.println("Nome da cidade: " + cidade.getNome());
            System.out.println("Codigo do estado: " + cidade.getEstado().getCodigo());
            System.out.println("Sigla do estado: " + cidade.getEstado().getSigla());
            System.out.println("Nome do estado: " + cidade.getEstado().getNome());
            System.out.println();
        }
    }

}
