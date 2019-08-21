package br.com.vendadireta.bean;

import br.com.vendadireta.dao.UsuarioDAO;
import br.com.vendadireta.entidade.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.apache.commons.codec.digest.DigestUtils;
import org.omnifaces.util.Messages;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 16/04/2016 - Classe: UsuarioBean
 */
@SuppressWarnings({ "serial", "unused" })
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario;
    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PostConstruct
    public void listar() {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarios = usuarioDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar os Usuarios");
            erro.printStackTrace();
        }
    }

    public void novo() {
        usuario = new Usuario();
    }

    public void salvar() throws Exception {
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            //usuario.setSenha(DigestUtils.md5Hex(usuario.getSenha()));
            usuarioDAO.merge(usuario);

            novo();
            usuarios = usuarioDAO.listar();

            Messages.addGlobalInfo("Usuário salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Já existe um Usuário com este CPF");
            erro.printStackTrace();
        } catch (Exception e) {
            throw new Exception("Já existe esse nome na base");
        }
    }

    public void excluir(ActionEvent evento) {

        try {
            usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.excluir(usuario);

            usuarios = usuarioDAO.listar();

            Messages.addGlobalInfo("Usuário removido com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar remover o Usuario");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
    }
}
