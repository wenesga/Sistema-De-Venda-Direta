package br.com.vendadireta.bean;

import br.com.vendadireta.dao.MarcaDAO;
import br.com.vendadireta.entidade.Marca;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 * @Cometario:  
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date   17/04/2016 - Classe: MarcaBean
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MarcaBean implements Serializable{
    
    private Marca marca;
    private List<Marca> marcas;

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }
    
    
    public void novo() {
        marca = new Marca();
    }

    
    @PostConstruct
    public void listar() {
        try {
            MarcaDAO marcaDAO = new MarcaDAO();
            marcas = marcaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar as Marcas");
            erro.printStackTrace();
        }
    }
    
    
    public void salvar() {
        try {
            MarcaDAO marcaDAO = new MarcaDAO();
            marcaDAO.merge(marca);

            novo();
            marcas = marcaDAO.listar();

            Messages.addGlobalInfo("Marca salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Marca");
            erro.printStackTrace();
        }
    }

    
    public void excluir(ActionEvent evento) {

        try {
            marca = (Marca) evento.getComponent().getAttributes().get("marcaSelecionado");

            MarcaDAO marcaDAO = new MarcaDAO();
            marcaDAO.excluir(marca);
            
            marcas = marcaDAO.listar();
            
            Messages.addGlobalInfo("Marca removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar remover a Marca");
            erro.printStackTrace();
        }
    }
    
    public void editar(ActionEvent evento){
        marca = (Marca) evento.getComponent().getAttributes().get("marcaSelecionado");
    }
}
