package br.com.vendadireta.bean;

import br.com.vendadireta.dao.LocalDAO;
import br.com.vendadireta.entidade.Local;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 01/05/2016 - Classe: LocalBean
 */
@SuppressWarnings({ "serial", "unused" })
@ManagedBean
@ViewScoped
public class LocalBean implements Serializable {

    private MapModel emptyModel;
    private Local local;
    private List<Local> locais;
    private String title;
    private double lat;
    private double lng;

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        emptyModel.addOverlay(marker);
        Messages.addGlobalInfo("Marcador adicionado: " + "Latitude: " + lat + " Longitude: " + lng);
    }

}
