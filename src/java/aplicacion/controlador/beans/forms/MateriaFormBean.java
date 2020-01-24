/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.CarreraBean;
import aplicacion.controlador.beans.MateriaBean;
import aplicacion.modelo.dominio.Carrera;
import aplicacion.modelo.dominio.Materia;
import aplicacion.modelo.util.ListMaterias;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
public class MateriaFormBean implements Serializable {
    
    private List<String> materias;
    
    @ManagedProperty(value = "#{materiaBean}")
    private MateriaBean materiaBean;
    
    @ManagedProperty(value = "#{carreraBean}")
    private CarreraBean carreraBean;
    /**
     * Creates a new instance of MateriaFormBean
     */
    public MateriaFormBean() {
        ListMaterias listaMaterias = new ListMaterias();
        materias = listaMaterias.getMaterias();
    }

    /**
     * Método que permite agregar una nueva materia
     * Antes de guardar, verifica si ya existe la materia
     * junto con su carrera asignada
     */
    public void agregarMateria() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        materiaBean.getMateria().setEstado(true);
        materiaBean.getMateria().setCarrera(carreraBean.getCarrera());
        if (validarMateria() == true) {
            materiaBean.agregarMateria();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.materiaAgreCorrect"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            materiaBean.setMateria(new Materia());
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("men.materiaAgreInvalid"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
    
    /**
     * Método que actualiza los datos de una materia
     */
    public void modificarMateria() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        materiaBean.modificarMateria();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.materiaActualizada"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('dialogEditarMateria').hide();");
        materiaBean.setMateria(new Materia());
    }
    
    /**
     * Método que elimina (borrado lógico) una materia
     */
    public void eliminarMateria() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        materiaBean.getMateria().setEstado(false);
        materiaBean.eliminarMateria();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.materiaEliminada"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarMateria').hide();");
    }
        
    /**
     * Método no usado
     */
    public List<String> completarMaterias(String query) {
        List<String> resultado = new ArrayList<>();
        for (int i = 0; i < materias.size(); i++) {
            String skin = materias.get(i);
            if (skin.toLowerCase().startsWith(query)) {
                resultado.add(skin);
            }
        }
        return resultado;
    }
    
    /**
     * Método que obtiene las materias de la tabla materias
     * @return una lista de materias
     */
    public List<Materia> obtenerMateria() {
        return materiaBean.obtenerMaterias();
    }
    
    /**
     * Método que obtiene las carreras de la tabla carreras
     * @return una lista de carreras
     */
    public List<Carrera> obtenerCarrera() {
        return carreraBean.obtenerCarreras();
    }
    
    /**
     * Método que verifica si ya existe la materia y carrera relacionada
     * @return false si la materia y su carrera relacionada no existe
     */
    public boolean validarMateria() {
        return materiaBean.validarMateria() == null;
    }
    
    
    /**
     *  Método que permite exportar el archivo pdf, una lista de materias
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     */
    public void exportarMateriasPdf(ActionEvent actionEvent) throws JRException, IOException{
        Map<String, Object> parametros = new HashMap<String, Object>();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/materiasReport.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(obtenerMateria()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=reportMaterias.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete(); 
    }
    
    /**
     *
     * @return the materiaBean
     */
    public MateriaBean getMateriaBean() {
        return materiaBean;
    }

    /**
     *
     * @param materiaBean the materiaBean to set
     */
    public void setMateriaBean(MateriaBean materiaBean) {
        this.materiaBean = materiaBean;
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
     * @return the materias
     */
    public List<String> getMaterias() {
        return materias;
    }

    /**
     * 
     * @param materias the materias to set
     */
    public void setMaterias(List<String> materias) {
        this.materias = materias;
    }
}
