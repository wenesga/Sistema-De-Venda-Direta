package br.com.vendadireta.bean;

import br.com.vendadireta.dao.ClienteDAO;
import br.com.vendadireta.dao.FormaPagamentoDAO;
import br.com.vendadireta.dao.PedidoDAO;
import br.com.vendadireta.entidade.Cliente;
import br.com.vendadireta.entidade.FormaPagamento;
import br.com.vendadireta.entidade.Pedido;
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
 * @date 22/04/2016 - Classe: PedidoBean
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PedidoBean implements Serializable {

    private Pedido pedido;
    private List<Pedido> pedidos;
    private List<Cliente> clientes;
    private List<FormaPagamento> formaPagamentos;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }

    public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }

    @PostConstruct
    public void listar() {
        try {
            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidos = pedidoDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Pedidos");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            pedido = new Pedido();
            ClienteDAO clienteDAO = new ClienteDAO();
            FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
            clientes = clienteDAO.listar();
            formaPagamentos = formaPagamentoDAO.listar();
            
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao gerar uma novo Pedido");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.merge(pedido);

            pedido = new Pedido();

            ClienteDAO clienteDAO = new ClienteDAO();
            FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();

            clientes = clienteDAO.listar();
            formaPagamentos = formaPagamentoDAO.listar();

            pedidos = pedidoDAO.listar();

            Messages.addGlobalInfo("Pedido salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar um novo Pedido");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            pedido = (Pedido) evento.getComponent().getAttributes().get("pedidoSelecionado");

            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.excluir(pedido);

            pedidos = pedidoDAO.listar();

            Messages.addGlobalInfo("Pedido removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar excluir o Pedido");
            erro.printStackTrace();
        }
    }
    
    public void editar(ActionEvent evento) {
        try {
            pedido = (Pedido) evento.getComponent().getAttributes().get("pedidoSelecionado");
            
            ClienteDAO clienteDAO = new ClienteDAO();
            FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
            
            clientes = clienteDAO.listar();
            formaPagamentos = formaPagamentoDAO.listar();
            
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um Pedido");
            erro.printStackTrace();
        }
    }

}
