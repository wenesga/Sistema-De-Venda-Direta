package br.com.vendadireta.bean;

import br.com.vendadireta.dao.UsuarioDAO;
import br.com.vendadireta.entidade.Usuario;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 28/04/2016 - Classe: AutenticacaoBean
 */
@ManagedBean
@SessionScoped
public class AutenticacaoBean {

    private Usuario usuario;
    private Usuario usuarioLogado;

    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void iniciar() {
        usuario = new Usuario();
    }

    public void autenticar() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();          
            usuarioLogado = usuarioDAO.autenticar(usuario.getCpf(), usuario.getSenha());
   
            if(usuarioLogado == null){
                Messages.addGlobalError("CPF ou Senha incorreto");
                return;
            } else {
                Messages.addFlashGlobalInfo("Usuário autenticado com sucesso" );
            }
 
            Faces.redirect("./pages/principal.xhtml");
            
        } catch (IOException erro) {
            erro.printStackTrace();
            Messages.addGlobalError(erro.getMessage());
        }
    }
    
    public String sair(){
        usuarioLogado = null;
        return "/pages/autenticacao.xhtml?faces-redirect=true";
    }
}
