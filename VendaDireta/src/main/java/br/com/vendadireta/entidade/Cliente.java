package br.com.vendadireta.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.br.CPF;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 02/04/2016 - Classe: Cliente
 */
@SuppressWarnings("serial")
@Entity
public class Cliente extends GenericEntidade {

    @Column(length = 100, nullable = false)
    private String nome;

    @CPF(message="O CPF informado é Inválido")
    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @ManyToOne
    private Local local;

    @Column(length = 14, nullable = false)
    private String telefone;

    @Column(length = 100, nullable = false)
    private String endereco;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Cidade cidade;

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
