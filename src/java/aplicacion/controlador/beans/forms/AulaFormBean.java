/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.AulaBean;
import aplicacion.modelo.dominio.Aula;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;

/**
 *
 * @author GASTON TOCONAS
 */
@ManagedBean
@ViewScoped
public class AulaFormBean implements Serializable {

    @ManagedProperty(value = "#{aulaBean}")
    private AulaBean aulaBean;
    
    /**
     * Creates a new instance of AulaFormBean
     */
    public AulaFormBean() {
    }
    
    /**
     * Método que permite guardar una nueva aula
     * Antes de guardar, verifica si ya existe el aula ingresada
     */
    public void registrarNuevaAula() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        aulaBean.getAula().setEstado(true);
        if (validarAula() == true) {
            aulaBean.agregarAula();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.aulaAgregadaCorrect"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            aulaBean.setAula(new Aula());
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("men.aulaAgregadaInvalid"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
    
    /**
     * Método que verifica si ya existe el aula
     * @return false si el aula no existe
     */
    public boolean validarAula() {
        return aulaBean.verificarAula() == null;
    }
    
    /**
     * Método que actualiza los datos de una aula
     */
    public void actualizarAula() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        aulaBean.modificarAula();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.aulaActualizada"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('dialogEditarAula').hide();");
    }
    
    /**
     * Método que obtiene las aulas de la tabla aulas
     * @return una lista de aulas
     */
    public List<Aula> obtenerAulas() {
        return aulaBean.obtenerAulas();
    }
    
    /**
     * Método que elimina (borrado lógico) una aula
     */
    public void eliminarAula() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        aulaBean.getAula().setEstado(false);
        aulaBean.eliminarAula();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.aulaEliminada"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarAula').hide();");
    }
        
    /**
     *  Método que permite exportar el archivo pdf, una lista de aulas
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     */
    public void exportarAulasPdf(ActionEvent actionEvent) throws JRException, IOException{
        Map<String, Object> parametros = new HashMap<String, Object>();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/aulasReport.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(obtenerAulas()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=reportAulas.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete(); 
    }

    /**
     *
     * @return the aula
     */
    public AulaBean getAulaBean() {
        return aulaBean;
    }

    /**
     *
     * @param aulaBean the aulaBean to set
     */
    public void setAulaBean(AulaBean aulaBean) {
        this.aulaBean = aulaBean;
    }
    
}
