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

public class ProdutoRelatorio<T> {

    private FacesContext context;
    private ByteArrayOutputStream baos;
    private HttpServletResponse response;
    private Class<T> classe;

    public ProdutoRelatorio(Class<T> classe) {
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
                       
            // FILTRO PRODUTOS
            String proNome = (String) filtros.get("nome");
            String fornNome = (String) filtros.get("fornecedor.nome");
            String proMarca = (String) filtros.get("marca.nome");
            String proCategoria = (String) filtros.get("categoria.nome");

            // CAMINHO DO BANNER DO RELATORIO
            String caminhoBanner = Faces.getRealPath("/resources/images/banner.png");
            
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("banner", caminhoBanner);
            
            // IF PRODUTO
            if (proNome == null) {
                params.put("PRODUTO_NOME", "%%");
            } else {
                params.put("PRODUTO_NOME", "%" + proNome + "%");
            }

            if (fornNome == null) {
                params.put("PRODUTO_FORNECEDOR", "%%");
            } else {
                params.put("PRODUTO_FORNECEDOR", "%" + fornNome + "%");
            }
     
            if (proMarca == null) {
                params.put("PRODUTO_MARCA", "%%");
            } else {
                params.put("PRODUTO_MARCA", "%" + proMarca + "%");
            }

            if (proCategoria == null) {
                params.put("PRODUTO_CATEGORIA", "%%");
            } else {
                params.put("PRODUTO_CATEGORIA", "%" + proCategoria + "%");
            }

            baos = new ByteArrayOutputStream();
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            JasperPrint print = JasperFillManager.fillReport(report, params, conexao);
            JasperExportManager.exportReportToPdfStream(print, baos);
            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename=produto.pdf");
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context.responseComplete();

        } catch (JRException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatorio");
            erro.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(ProdutoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String target() {
        switch (classe.getSimpleName()) {
            case "Produto":
                return "produto.jasper";
        }
        return null;
    }
}
