package br.com.vendadireta.entidade;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 02/04/2016 - Classe: Pedido
 */
@SuppressWarnings("serial")
@Entity
public class Pedido extends GenericEntidade {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FormaPagamento forma_pagamento;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_pedido;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date prazo_pagamento;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return forma_pagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.forma_pagamento = formaPagamento;
    }

    public Date getData_pedido() {
        return data_pedido;
    }

    public void setData_pedido(Date data_pedido) {
        this.data_pedido = data_pedido;
    }

    public Date getPrazo_pagamento() {
        return prazo_pagamento;
    }

    public void setPrazo_pagamento(Date prazo_pagamento) {
        this.prazo_pagamento = prazo_pagamento;
    }

}
