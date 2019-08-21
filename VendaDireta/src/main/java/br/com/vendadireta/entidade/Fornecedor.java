package br.com.vendadireta.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 12/04/2016 - Classe: Fornecedor
 */
@SuppressWarnings("serial")
@Entity
public class Fornecedor extends GenericEntidade {

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String tipo;

    @Column(length = 100, nullable = false)
    private String endereco;

    @Column(length = 14, nullable = false)
    private String telefone;

//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private Estado estado;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cidade cidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

//    public Estado getEstado() {
//        return estado;
//    }
//
//    public void setEstado(Estado estado) {
//        this.estado = estado;
//    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
