package br.com.vendadireta.bean;

import br.com.vendadireta.dao.CidadeDAO;
import br.com.vendadireta.dao.ClienteDAO;
import br.com.vendadireta.dao.EstadoDAO;
import br.com.vendadireta.entidade.Cidade;
import br.com.vendadireta.entidade.Cliente;
import br.com.vendadireta.entidade.Estado;
import br.com.vendadireta.relatorio.ClienteRelatorio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 19/04/2016 - Classe: ClienteBean
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

    private Cliente cliente;
    private List<Cliente> clientes;
    private Estado estado;
    private List<Estado> estados;
    private List<Cidade> cidades;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    @PostConstruct
    public void listar() {
        try {

            ClienteDAO clienteDAO = new ClienteDAO();
            clientes = clienteDAO.listar("nome");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Clientes");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            cliente = new Cliente();

            estado = new Estado();

            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

            cidades = new ArrayList<Cidade>();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao gerar um novo Cliente");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.merge(cliente);

            clientes = clienteDAO.listar("nome");

            cliente = new Cliente();

            estado = new Estado();

            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

            cidades = new ArrayList<>();

            Messages.addGlobalInfo("Cliente salvo com sucesso");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Já existe um Cliente com este CPF");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.excluir(cliente);

            clientes = clienteDAO.listar();

            Messages.addGlobalInfo("Cliente removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar excluir o Cliente");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

            estado = cliente.getCidade().getEstado();

            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

            CidadeDAO cidadeDAO = new CidadeDAO();
            cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um Cliente");
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

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void imprimir() {
        ClienteRelatorio relatorio = new ClienteRelatorio(Cliente.class);
        relatorio.print();
    }
}
