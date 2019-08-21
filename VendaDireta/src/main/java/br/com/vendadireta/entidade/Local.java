package br.com.vendadireta.entidade;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @Cometario:  
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date   01/05/2016 - Classe: Local
 */
@SuppressWarnings("serial")
@Entity
public class Local extends GenericEntidade{

    @Column(length = 100, nullable = false)
    private String descricao;
    
    @Column(nullable = false, precision = 20, scale = 17)
    private BigDecimal latitude;
    
    @Column(nullable = false, precision = 20, scale = 17)
    private BigDecimal longitude;    

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
