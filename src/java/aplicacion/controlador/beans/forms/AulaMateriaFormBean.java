/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.AulaBean;
import aplicacion.controlador.beans.AulaMateriaBean;
import aplicacion.controlador.beans.DocenteMateriaBean;
import aplicacion.modelo.dominio.Aula;
import aplicacion.modelo.dominio.AulaMateria;
import aplicacion.modelo.dominio.DocenteMateria;
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
public class AulaMateriaFormBean implements Serializable {     
    @ManagedProperty(value = "#{aulaMateriaBean}")
    private AulaMateriaBean aulaMateriaBean;
    
    @ManagedProperty(value = "#{aulaBean}")
    private AulaBean aulaBean;
        
    @ManagedProperty(value = "#{docenteMateriaBean}")
    private DocenteMateriaBean docenteMateriaBean;
        
    /**
     * Creates a new instance of AulaMateriaFormBean
     */
    public AulaMateriaFormBean() {
    }
    
    /**
     * Método que permite guardar una AulaMateria
     * Antes de guardar, verifica si la materia ya tiene una aula asignada
     */
    public void agregarAulaMateria() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        aulaMateriaBean.getAulaMateria().setAula(aulaBean.getAula());
        aulaMateriaBean.getAulaMateria().setDocenteMateria(docenteMateriaBean.getDocenteMateria());
        aulaMateriaBean.getAulaMateria().setEstado(true);
        if (validarAulaMateria() == true) {
            aulaMateriaBean.agregarAulaMateria();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.aulaMateriaAgreCorrect"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            aulaBean.setAula(new Aula());
            docenteMateriaBean.setDocenteMateria(new DocenteMateria());
            aulaMateriaBean.setAulaMateria(new AulaMateria());
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("men.aulaMateriaAgreInvalid"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
    
    /**
     * MMétodo que actualiza los datos de un AulaMateria
     */
    public void modificarAulaMateria() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        aulaMateriaBean.modificarAulaMateria();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.aulaMateriaModificada"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        aulaMateriaBean.setAulaMateria(new AulaMateria());
        RequestContext.getCurrentInstance().execute("PF('dialogEditar').hide();");    
    }
    
    /**
     * Método que elimina (borrado lógico) un AulaMateria
     */
    public void eliminarAulaMateria() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        aulaMateriaBean.getAulaMateria().setEstado(false);
        aulaMateriaBean.eliminarAulaMateria();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.aulaMateriaEliminada"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);   
        RequestContext.getCurrentInstance().execute("PF('dialogEliminar').hide();");
    }
    
    /**
     * Método que verifica si ya existe el AulaMateria
     * @return false si el AulaMateria no existe
     */
    public boolean validarAulaMateria() {
        return aulaMateriaBean.validarAulaMateria() == null;
    }
    
    /**
     * Método que obtiene las AulasMaterias de la tabla AulasMaterias
     * @return una lista de AulasMaterias
     */
    public List<AulaMateria> obtenerAulaMateria() {
        return aulaMateriaBean.obtenerAulaMateria();
    }
        
    /**
     *  Método que permite exportar el archivo pdf, una lista de aulaMaterias
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     */
    public void exportarAulaMateriaPdf(ActionEvent actionEvent) throws JRException, IOException{
        Map<String, Object> parametros = new HashMap<String, Object>();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/aulaMateriaReport.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(obtenerAulaMateria()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=reportAulaMateria.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete(); 
    }
    
    /**
     *
     * @return the aulaMateriaBean
     */
    public AulaMateriaBean getAulaMateriaBean() {
        return aulaMateriaBean;
    }

    /**
     *
     * @param aulaMateriaBean the aulaMateriaBean to set
     */
    public void setAulaMateriaBean(AulaMateriaBean aulaMateriaBean) {
        this.aulaMateriaBean = aulaMateriaBean;
    }

    /**
     *
     * @return the aulaBean
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

    /**
     *
     * @return the docenteMateriaBean
     */
    public DocenteMateriaBean getDocenteMateriaBean() {
        return docenteMateriaBean;
    }

    /**
     *
     * @param docenteMateriaBean the docenteMateriaBean to set
     */
    public void setDocenteMateriaBean(DocenteMateriaBean docenteMateriaBean) {
        this.docenteMateriaBean = docenteMateriaBean;
    }    
    
}
