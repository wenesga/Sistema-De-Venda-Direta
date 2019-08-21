package br.com.vendadireta.bean;

import br.com.vendadireta.dao.FormaPagamentoDAO;
import br.com.vendadireta.entidade.FormaPagamento;
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
 * @date   21/04/2016 - Classe: FormaPagamentoBean
 */
@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FormaPagamentoBean implements Serializable{
    
    private FormaPagamento formaPagamento;
    private List<FormaPagamento> formaPagamentos;

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }

    public void setFormaPagamentos(List<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }
    
    @PostConstruct
    public void listar(){
        try{
            FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
            formaPagamentos = formaPagamentoDAO.listar();
        }catch(RuntimeException erro){
            Messages.addGlobalError("Ocorreu um erro ao tentar listar as Formas de Pagamentos");
            erro.printStackTrace();
        }
    }
    
    public void novo() {
        formaPagamento = new FormaPagamento();
    }

    
    public void salvar() {
        try {
            FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
            formaPagamentoDAO.merge(formaPagamento);

            novo();
            formaPagamentos = formaPagamentoDAO.listar();

            Messages.addGlobalInfo("Forma de Pagamento salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar a Forma de Pagamento");
            erro.printStackTrace();
        }
    }
    
    public void excluir(ActionEvent evento) {

        try {
            formaPagamento = (FormaPagamento) evento.getComponent().getAttributes().get("formaPagamentoSelecionado");

            FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
            formaPagamentoDAO.excluir(formaPagamento);
            
            formaPagamentos = formaPagamentoDAO.listar();
            
            Messages.addGlobalInfo("Forma de Pagamento removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar remover a Forma de Pagamento");
            erro.printStackTrace();
        }
    }
    
    public void editar(ActionEvent evento){
        formaPagamento = (FormaPagamento) evento.getComponent().getAttributes().get("formaPagamentoSelecionado");
    }
}
