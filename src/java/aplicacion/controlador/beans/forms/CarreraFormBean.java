/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.CarreraBean;
import aplicacion.modelo.dominio.Carrera;
import aplicacion.modelo.util.ListCarreras;
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
public class CarreraFormBean implements Serializable {
     
    private List<String> carreras;
    
    @ManagedProperty(value = "#{carreraBean}")
    private CarreraBean carreraBean;
    
    /**
     * Creates a new instance of CarreraFormBean
     */
    public CarreraFormBean() {
        ListCarreras listaCarreras = new ListCarreras();
        carreras = listaCarreras.getCarreras();
    }
       
    /**
     * Método que permite guardar una carrera
     * Antes de guardar, verifica si ya existe la carrera y el plan
     */
    public void registrarNuevaCarrera() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        carreraBean.getCarrera().setEstado(true);
        if (validarCarrera() == true) {
            carreraBean.agregarCarrera();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.carreraAgreCorrect"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            carreraBean.setCarrera(new Carrera());
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("men.carreraAgreInvalid"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
    
    /**
     * Método que verifica si ya existe la carrera
     * @return false si la carrera no existe
     */
    public boolean validarCarrera() {
        return carreraBean.validarCarrera() == null;
    }
    
    /**
     * Método que actualiza los datos de una carrera
     */
    public void actualizarCarrera() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        if (validarCarrera() == true) {
            carreraBean.modificarCarrera();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.carreraActualizada"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            RequestContext.getCurrentInstance().execute("PF('dialogEditarCarrera').hide();");
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("men.carreraAgreInvalid"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }    
    
    /**
     * Método que obtiene las carreras de la tabla carreras
     * @return una lista de carreras
     */
    public List<Carrera> obtenerCarreras() {
        return carreraBean.obtenerCarreras();
    }
    
    /**
     * Método que elimina (borrado lógico) una carrera
     */
    public void eliminarCarrera() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        carreraBean.getCarrera().setEstado(false);
        carreraBean.eliminarCarrera();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.carreraEliminada"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarCarrera').hide();");
    }
    
    /**
     *  Método que permite exportar el archivo pdf, una lista de carreras
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     */
    public void exportarCarrerasPdf(ActionEvent actionEvent) throws JRException, IOException{
        Map<String, Object> parametros = new HashMap<String, Object>();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/carrerasReport.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(obtenerCarreras()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=reportCarreras.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete(); 
    }
    
    
    /**
     *
     * @return the carreraBean
     */
    public CarreraBean getCarreraBean() {
        return carreraBean;
    }

    /**
     *
     * @param carreraBean the carreraBean to set
     */
    public void setCarreraBean(CarreraBean carreraBean) {
        this.carreraBean = carreraBean;
    }

    /**
     *
     * @return the carreras
     */
    public List<String> getCarreras() {
        return carreras;
    }

    /**
     *
     * @param carreras the carreras to set
     */
    public void setCarreras(List<String> carreras) {
        this.carreras = carreras;
    }
}
