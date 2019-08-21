package br.com.vendadireta.bean;

import br.com.vendadireta.dao.CidadeDAO;
import br.com.vendadireta.dao.EstadoDAO;
import br.com.vendadireta.dao.FornecedorDAO;
import br.com.vendadireta.entidade.Cidade;
import br.com.vendadireta.entidade.Estado;
import br.com.vendadireta.entidade.Fornecedor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 * @Cometario: Metódos: listar, novo, salvar excluir, editar, popular (Cidades do estado selecionado).
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 17/04/2016 - Classe: FornecedorBean
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FornecedorBean implements Serializable {

    private Fornecedor fornecedor;
    private List<Fornecedor> fornecedores;
    private Estado estado;
    private List<Estado> estados;
    private List<Cidade> cidades;

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @PostConstruct
    public void listar() {
        try {

            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedores = fornecedorDAO.listar("nome");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Fornecedores");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            fornecedor = new Fornecedor();

            estado = new Estado();
            
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

            cidades = new ArrayList<Cidade>();
        
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao gerar um novo Fornecedor");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedorDAO.merge(fornecedor);

            fornecedores = fornecedorDAO.listar("nome");
            
            fornecedor = new Fornecedor();
            
            estado = new Estado();

            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

            cidades = new ArrayList<>();
            
            Messages.addGlobalInfo("Fornecedor salvo com sucesso");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao salvar um novo Fornecedor");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");

            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedorDAO.excluir(fornecedor);

            fornecedores = fornecedorDAO.listar();

            Messages.addGlobalInfo("Fornecedor removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar excluir o Fornecedor");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            fornecedor = (Fornecedor) evento.getComponent().getAttributes().get("fornecedorSelecionado");

            estado = fornecedor.getCidade().getEstado();
            
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

            CidadeDAO cidadeDAO = new CidadeDAO();
            cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um Fornecedor");
            erro.printStackTrace();
        }
    }

    public void popular() {
        try {
            if (estado != null) {
                CidadeDAO cidadeDAO = new CidadeDAO();
                cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());    
            } else {
                cidades = new ArrayList<>();
            }
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as Cidades");
            erro.printStackTrace();
        }
    }
}
