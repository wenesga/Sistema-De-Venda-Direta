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
 * @date 19/05/2016 - Classe: ClienteRelatorio
 */
public class ClienteRelatorio<T> {

    private FacesContext context;
    private ByteArrayOutputStream baos;
    private HttpServletResponse response;
    private Class<T> classe;

    public ClienteRelatorio(Class<T> classe) {
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

            // FILTRO CLIENTES
            String clienteNome = (String) filtros.get("nome");
            String clienteCpf = (String) filtros.get("cpf");

            // CAMINHO DO BANNER DO RELATORIO
            String caminhoBanner = Faces.getRealPath("/resources/images/banner.png");
            
            Map<String, Object> params = new HashMap<String, Object>();
            
            params.put("banner", caminhoBanner);

            // IF CLIENTE
            if (clienteNome == null) {
                params.put("CLIENTE_NOME", "%%");
            } else {
                params.put("CLIENTE_NOME", "%" + clienteNome + "%");
            }

            if (clienteCpf == null) {
                params.put("CLIENTE_CPF", "%%");
            } else {
                params.put("CLIENTE_CPF", "%" + clienteCpf + "%");
            }
 
            baos = new ByteArrayOutputStream();
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            JasperPrint print = JasperFillManager.fillReport(report, params, conexao);
            JasperExportManager.exportReportToPdfStream(print, baos);
            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename=cliente.pdf");
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context.responseComplete();

        } catch (JRException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatorio");
            erro.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(ClienteRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String target() {
        switch (classe.getSimpleName()) {
            case "Cliente":
                return "cliente.jasper";
        }
        return null;
    }
}
