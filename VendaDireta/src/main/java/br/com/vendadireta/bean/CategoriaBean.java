package br.com.vendadireta.bean;

import br.com.vendadireta.dao.CategoriaDAO;
import br.com.vendadireta.entidade.Categoria;
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
 * @date   17/04/2016 - Classe: CategoriaBean
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CategoriaBean implements Serializable{
    
    private Categoria categoria;
    private List<Categoria> categorias;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategoiras() {
        return categorias;
    }

    public void setCategoiras(List<Categoria> categoiras) {
        this.categorias = categoiras;
    }

 
    public void novo() {
        categoria = new Categoria();
    }

    
    @PostConstruct
    public void listar() {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categorias = categoriaDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar as Categorias");
            erro.printStackTrace();
        }
    }
    
    
    public void salvar() {
        try {
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoriaDAO.merge(categoria);

            novo();
            categorias = categoriaDAO.listar();

            Messages.addGlobalInfo("Categoria salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Categoria");
            erro.printStackTrace();
        }
    }

    
    public void excluir(ActionEvent evento) {

        try {
            categoria = (Categoria) evento.getComponent().getAttributes().get("categoriaSelecionado");

            CategoriaDAO categoriaDAO = new CategoriaDAO();
            categoriaDAO.excluir(categoria);
            
            categorias = categoriaDAO.listar();
            
            Messages.addGlobalInfo("Categoria removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar remover a Categoria");
            erro.printStackTrace();
        }
    }
    
    public void editar(ActionEvent evento){
        categoria = (Categoria) evento.getComponent().getAttributes().get("categoriaSelecionado");
    }
}
