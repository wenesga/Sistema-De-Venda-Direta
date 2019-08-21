package br.com.vendadireta.bean;

import br.com.vendadireta.dao.CategoriaDAO;
import br.com.vendadireta.dao.FornecedorDAO;
import br.com.vendadireta.dao.MarcaDAO;
import br.com.vendadireta.dao.ProdutoDAO;
import br.com.vendadireta.entidade.Categoria;
import br.com.vendadireta.entidade.Fornecedor;
import br.com.vendadireta.entidade.Marca;
import br.com.vendadireta.entidade.Produto;
import br.com.vendadireta.relatorio.ProdutoRelatorio;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 20/04/2016 - Classe: ProdutoBean
 */
@SuppressWarnings({ "serial", "unused" })
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

    private Produto produto;

    private List<Produto> produtos;
    private List<Categoria> categorias;
    private List<Fornecedor> fornecedores;
    private List<Marca> marcas;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    @PostConstruct
    public void listar() {
        try {

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtos = produtoDAO.listar("nome");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Produtos");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            produto = new Produto();

            MarcaDAO marcaDAO = new MarcaDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            FornecedorDAO fornecedorDAO = new FornecedorDAO();

            marcas = marcaDAO.listar("nome");
            categorias = categoriaDAO.listar("nome");
            fornecedores = fornecedorDAO.listar("nome");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao gerar um novo Produto");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.merge(produto);

            produto = new Produto();

            produtos = produtoDAO.listar("nome");

            MarcaDAO marcaDAO = new MarcaDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            FornecedorDAO fornecedorDAO = new FornecedorDAO();

            marcas = marcaDAO.listar("nome");
            categorias = categoriaDAO.listar("nome");
            fornecedores = fornecedorDAO.listar("nome");

            Messages.addGlobalInfo("Produto salvo com sucesso");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao salvar um novo Produto");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.excluir(produto);

            produtos = produtoDAO.listar("nome");

            Messages.addGlobalInfo("Produto removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar excluir o Produto");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

            MarcaDAO marcaDAO = new MarcaDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            FornecedorDAO fornecedorDAO = new FornecedorDAO();

            marcas = marcaDAO.listar("nome");
            categorias = categoriaDAO.listar("nome");
            fornecedores = fornecedorDAO.listar("nome");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um Produto");
            erro.printStackTrace();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void imprimir() {
        ProdutoRelatorio relatorio = new ProdutoRelatorio(Produto.class);
        relatorio.print();
    }
}
