package br.com.vendadireta.entidade;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 25/04/2016 - Classe: Venda
 */
@SuppressWarnings("serial")
@Entity
public class Venda extends GenericEntidade {

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horario;

    @Column(nullable = false, precision = 7, scale = 2)
    private BigDecimal valorTotal;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "venda")
    @Fetch(FetchMode.SUBSELECT)
    private List<ItemVenda> itensVenda;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private FormaPagamento forma_pagamento;

    public FormaPagamento getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(FormaPagamento forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
