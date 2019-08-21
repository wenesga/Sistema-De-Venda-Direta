package br.com.vendadireta.entidade;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 02/04/2016 - Classe: Produto
 */
@SuppressWarnings("serial")
@Entity
public class Produto extends GenericEntidade {

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal valor_compra;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal valor_venda;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Marca marca;

    @Column(nullable = false)
    private Short estoque;

    @Column(nullable = false)
    private Short estoque_minimo;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Fornecedor fornecedor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(BigDecimal valor_compra) {
        this.valor_compra = valor_compra;
    }

    public BigDecimal getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(BigDecimal valor_venda) {
        this.valor_venda = valor_venda;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Short getEstoque() {
        return estoque;
    }

    public void setEstoque(Short estoque) {
        this.estoque = estoque;
    }

    public Short getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(Short estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
