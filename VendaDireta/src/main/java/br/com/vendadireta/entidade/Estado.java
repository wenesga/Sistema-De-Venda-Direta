package br.com.vendadireta.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 03/04/2016 - Classe: Estado
 */
@SuppressWarnings("serial")
@Entity
public class Estado extends GenericEntidade {

    @Column(length = 2, nullable = false)
    private String sigla;

    @Column(length = 50, nullable = false)
    private String nome;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
