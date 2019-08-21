package br.com.vendadireta.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @Cometario: Produtos pedidos
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 03/04/2016 - Classe: ProdutoPedido
 */
@SuppressWarnings("serial")
@Entity
public class ProdutoPedido extends GenericEntidade {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;

    @Column(nullable = false)
    private Short quantidade_produto;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Short getQuantidade_produto() {
        return quantidade_produto;
    }

    public void setQuantidade_produto(Short quantidade_produto) {
        this.quantidade_produto = quantidade_produto;
    }
}
