package br.com.vendadireta.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.br.CPF;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 02/04/2016 - Classe: Usuario
 */
@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericEntidade {

    @Column(length = 50, nullable = false)
    private String nome;

    @CPF(message="O CPF informado é inválido")
    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(length = 32, nullable = false)
    private String senha;
    
    @Transient
    private String senhaSemCriptografia;

    
    
    public String getSenhaSemCriptografia() {
        return senhaSemCriptografia;
    }

    public void setSenhaSemCriptografia(String senhaSemCriptografia) {
        this.senhaSemCriptografia = senhaSemCriptografia;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
