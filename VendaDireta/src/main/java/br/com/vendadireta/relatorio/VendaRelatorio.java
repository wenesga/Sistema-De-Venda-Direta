package br.com.vendadireta.relatorio;

import br.com.vendadireta.util.HibernateUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;

/**
 * @Cometario:
 * @author Wenes Gomes Aquino <wenesga@gmail.com>
 * @date 21/05/2016 - Classe: VendaRelatorio
 */
public class VendaRelatorio<T> {

    private FacesContext context;
    private ByteArrayOutputStream baos;
    private HttpServletResponse response;
    private Class<T> classe;

    public VendaRelatorio(Class<T> classe) {
        this.context = FacesContext.getCurrentInstance();
        response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        this.classe = classe;
    }

    public void print() {
        try {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("relatorio/" + target());
            Connection conexao = HibernateUtil.getConexao();

            DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
            Map<String, Object> filtros = tabela.getFilters();

            String vendaCliente = (String) filtros.get("cliente.nome");
            String vendaPagamento = (String) filtros.get("forma_pagamento.tipo");
            String vendaUsuario = (String) filtros.get("usuario.nome");

            // CAMINHO DO BANNER DO RELATORIO
            String caminhoBanner = Faces.getRealPath("/resources/images/banner.png");
            
            Map<String, Object> params = new HashMap<String, Object>();
            
            params.put("banner", caminhoBanner);

            if (vendaCliente == null) {
                params.put("VENDA_CLIENTE", "%%");
            } else {
                params.put("VENDA_CLIENTE", "%" + vendaCliente + "%");
            }
            
            if (vendaPagamento == null) {
                params.put("VENDA_PAGAMENTO", "%%");
            } else {
                params.put("VENDA_PAGAMENTO", "%" + vendaPagamento + "%");
            }
            
            if (vendaUsuario == null) {
                params.put("VENDA_USUARIO", "%%");
            } else {
                params.put("VENDA_USUARIO", "%" + vendaUsuario + "%");
            }

            baos = new ByteArrayOutputStream();
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            JasperPrint print = JasperFillManager.fillReport(report, params, conexao);
            JasperExportManager.exportReportToPdfStream(print, baos);
            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename=venda.pdf");
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context.responseComplete();

        } catch (JRException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatorio");
            erro.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(VendaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String target() {
        switch (classe.getSimpleName()) {
            case "Venda":
                return "vendaCliente.jasper";
        }
        return null;
    }
}
