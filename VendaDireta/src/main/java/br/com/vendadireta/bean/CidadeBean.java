package br.com.vendadireta.bean;

import br.com.vendadireta.dao.CidadeDAO;
import br.com.vendadireta.dao.EstadoDAO;
import br.com.vendadireta.entidade.Cidade;
import br.com.vendadireta.entidade.Estado;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 14/04/2016 - Classe: CidadeBean
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

    private Cidade cidade;
    private List<Cidade> cidades;
    private List<Estado> estados;

    
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    
    @PostConstruct
    public void listar() {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidades = cidadeDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Estados");
            erro.printStackTrace();
        }
    }

    
    public void novo() {
        try {
            cidade = new Cidade();            
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");
            
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao gerar uma nova Cidade");
            erro.printStackTrace();
        }
    }

    
    public void salvar() {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.merge(cidade);

            cidade = new Cidade();

            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar();
            
            cidades = cidadeDAO.listar();

            Messages.addGlobalInfo("Cidade salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar uma nova Cidade");
            erro.printStackTrace();
        }
    }

    
    public void excluir(ActionEvent evento) {
        try {
            cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionado");
            
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.excluir(cidade);
            
            cidades = cidadeDAO.listar();

            Messages.addGlobalInfo("Cidade removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar excluir a Cidade");
            erro.printStackTrace();
        }
    }

    
    public void editar(ActionEvent evento) {
        try {
            cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionado");
            
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");
            
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma Cidade");
            erro.printStackTrace();
        }
    }
}
