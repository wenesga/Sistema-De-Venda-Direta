package br.com.vendadireta.bean;

import br.com.vendadireta.dao.ClienteDAO;
import br.com.vendadireta.dao.FormaPagamentoDAO;
import br.com.vendadireta.dao.ProdutoDAO;
import br.com.vendadireta.dao.UsuarioDAO;
import br.com.vendadireta.dao.VendaDAO;
import br.com.vendadireta.entidade.Cliente;
import br.com.vendadireta.entidade.FormaPagamento;
import br.com.vendadireta.entidade.ItemVenda;
import br.com.vendadireta.entidade.Produto;
import br.com.vendadireta.entidade.Usuario;
import br.com.vendadireta.entidade.Venda;
import br.com.vendadireta.relatorio.VendaRelatorio;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 27/04/2016 - Classe: VendaBean
 */
@SuppressWarnings({ "serial", "unused" })
@ManagedBean
@ViewScoped
public class VendaBean implements Serializable {

    private List<Produto> produtos;
    private List<ItemVenda> itensVenda;
    private Venda venda;
    private List<Cliente> clientes;
    private List<FormaPagamento> formaPagamentos;
    private List<Venda> vendas;
    private ItemVenda itemVenda;
    @ManagedProperty(value = "#{autenticacaoBean}")
    private AutenticacaoBean autenticacaoBean;

    public List<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }

    public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }

    public ItemVenda getItemVenda() {
        return itemVenda;
    }

    public void setItemVenda(ItemVenda itemVenda) {
        this.itemVenda = itemVenda;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public AutenticacaoBean getAutenticacaoBean() {
        return autenticacaoBean;
    }

    public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
        this.autenticacaoBean = autenticacaoBean;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    //@PostConstruct
    public void novo() {
        try {
            venda = new Venda();
            venda.setValorTotal(new BigDecimal("0.00"));

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtos = produtoDAO.listar("nome");
            itensVenda = new ArrayList<>();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de Venda");
            erro.printStackTrace();
        }
    }

    public void listar() {
        try {
            VendaDAO vendaDAO = new VendaDAO();
            vendas = vendaDAO.listar("horario");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar carregar a lista de Venda");
            erro.printStackTrace();
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void imprimir() {
        VendaRelatorio relatorio = new VendaRelatorio(Venda.class);
        relatorio.print();
    }

    public void adicionar(ActionEvent evento) {

        Produto produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

        int achou = -1;
        for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
            if (itensVenda.get(posicao).getProduto().equals(produto)) {
                achou = posicao;
            }
        }

        if (achou < 0) {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setValorParcial(produto.getValor_venda());
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(new Short("1"));

            itensVenda.add(itemVenda);
        } else {
            ItemVenda itemVenda = itensVenda.get(achou);
            itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));
            itemVenda.setValorParcial(produto.getValor_venda().multiply(new BigDecimal(itemVenda.getQuantidade())));
        }

        calcular();
    }

    public void remover(ActionEvent evento) {

        ItemVenda itemVenda = (ItemVenda) evento.getComponent().getAttributes().get("itemSelecionado");
        int achou = -1;
        for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
            if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {
                achou = posicao;
            }
        }

        if (achou > -1 && itemVenda.getQuantidade() > 1) {
            itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() - 1 + ""));
            itemVenda.setValorParcial(itemVenda.getValorParcial().subtract(itemVenda.getProduto().getValor_venda()));

        } else {
            itensVenda.remove(achou);
        }

        calcular();
    }

    public void calcular() {
        venda.setValorTotal(new BigDecimal("0.00"));

        for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
            ItemVenda itemVenda = itensVenda.get(posicao);
            venda.setValorTotal(venda.getValorTotal().add(itemVenda.getValorParcial()));
        }
    }

    public void finalizar() {
        try {
            venda.setHorario(new Date());
            venda.setCliente(null);
            venda.setForma_pagamento(null);
            venda.setUsuario(autenticacaoBean.getUsuarioLogado());

            //UsuarioDAO usuarioDAO = new UsuarioDAO();
            //Usuario usuario = usuarioDAO.buscar(autenticacaoBean.getUsuarioLogado().getCodigo());

            ClienteDAO clienteDAO = new ClienteDAO();
            clientes = clienteDAO.listar("nome");
            FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
            formaPagamentos = formaPagamentoDAO.listar();
            

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar finalizar a venda");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            if (venda.getValorTotal().signum() == 0) {
                Messages.addGlobalError("Informe pelo menos um item para a venda");
                return;
            }

            VendaDAO vendaDAO = new VendaDAO();
            vendaDAO.salvar(venda, itensVenda);
            novo();
            Messages.addGlobalInfo("Venda realizada com sucesso");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar a venda");
            erro.printStackTrace();
            novo();
        }
    }
}
