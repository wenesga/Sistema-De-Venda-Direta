package br.com.vendadireta.util;

import br.com.vendadireta.bean.AutenticacaoBean;
import br.com.vendadireta.entidade.Usuario;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.omnifaces.util.Faces;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 29/04/2016 - Classe: AutenticacaoListener
 */
@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        String paginaAtual = Faces.getViewId();

        boolean ehPaginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml");

        if (!ehPaginaDeAutenticacao) {
            AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

            if (autenticacaoBean == null) {
                Faces.navigate("/pages/autenticacao.xhtml");
                return;
            }

            Usuario usuario = autenticacaoBean.getUsuarioLogado();
            if (usuario == null) {
                Faces.navigate("/pages/autenticacao.xhtml");
                return;
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
