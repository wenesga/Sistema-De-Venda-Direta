package br.com.vendadireta.bean;

import br.com.vendadireta.dao.PedidoDAO;
import br.com.vendadireta.dao.ProdutoDAO;
import br.com.vendadireta.dao.ProdutoPedidoDAO;
import br.com.vendadireta.entidade.Pedido;
import br.com.vendadireta.entidade.Produto;
import br.com.vendadireta.entidade.ProdutoPedido;
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
 * @date 26/04/2016 - Classe: ProdutoPedido
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoPedidoBean implements Serializable {

    private ProdutoPedido produtoPedido;
    private List<ProdutoPedido> produtoPedidos;
    private List<Produto> produtos;
    private List<Pedido> pedidos;

    public ProdutoPedido getProdutoPedido() {
        return produtoPedido;
    }

    public void setProdutoPedido(ProdutoPedido produtoPedido) {
        this.produtoPedido = produtoPedido;
    }

    public List<ProdutoPedido> getProdutoPedidos() {
        return produtoPedidos;
    }

    public void setProdutoPedidos(List<ProdutoPedido> produtoPedidos) {
        this.produtoPedidos = produtoPedidos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @PostConstruct
    public void listar() {
        try {
            ProdutoPedidoDAO produtoPedidoDAO = new ProdutoPedidoDAO();
            produtoPedidos = produtoPedidoDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Produtos Pedidos");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            produtoPedido = new ProdutoPedido();
            PedidoDAO pedidoDAO = new PedidoDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            pedidos = pedidoDAO.listar();
            produtos = produtoDAO.listar();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao gerar uma novo Produtos Pedido");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            ProdutoPedidoDAO produtoPedidoDAO = new ProdutoPedidoDAO();
            produtoPedidoDAO.merge(produtoPedido);
            produtoPedido = new ProdutoPedido();
            PedidoDAO pedidoDAO = new PedidoDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            pedidos = pedidoDAO.listar();
            produtos = produtoDAO.listar();
            produtoPedidos = produtoPedidoDAO.listar();
            
            Messages.addGlobalInfo("Produtos Pedido salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar um novo Produtos Pedido");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento) {
        try {
            produtoPedido = (ProdutoPedido) evento.getComponent().getAttributes().get("produtoPedidoSelecionado");
            ProdutoPedidoDAO produtoPedidoDAO = new ProdutoPedidoDAO();
            produtoPedidoDAO.excluir(produtoPedido);
            produtoPedidos = produtoPedidoDAO.listar();

            Messages.addGlobalInfo("Produtos Pedido removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar excluir o Produto Pedido");
            erro.printStackTrace();
        }
    }
    
    public void editar(ActionEvent evento) {
        try {
            produtoPedido = (ProdutoPedido) evento.getComponent().getAttributes().get("produtoPedidoSelecionado");
            
            PedidoDAO pedidoDAO = new PedidoDAO();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            pedidos = pedidoDAO.listar();
            produtos = produtoDAO.listar();
            
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um Produto Pedido");
            erro.printStackTrace();
        }
    }
}
