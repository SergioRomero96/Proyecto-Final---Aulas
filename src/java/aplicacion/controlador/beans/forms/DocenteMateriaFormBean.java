/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.CarreraBean;
import aplicacion.controlador.beans.DocenteBean;
import aplicacion.controlador.beans.DocenteMateriaBean;
import aplicacion.controlador.beans.MateriaBean;
import aplicacion.modelo.dominio.Carrera;
import aplicacion.modelo.dominio.Docente;
import aplicacion.modelo.dominio.DocenteMateria;
import aplicacion.modelo.dominio.Materia;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class DocenteMateriaFormBean implements Serializable {
    @ManagedProperty(value = "#{docenteMateriaBean}")
    private DocenteMateriaBean docenteMateriaBean;
    
    @ManagedProperty(value = "#{docenteBean}")
    private DocenteBean docenteBean;
    
    @ManagedProperty(value = "#{carreraBean}")
    private CarreraBean carreraBean;
    
    @ManagedProperty(value = "#{materiaBean}")
    private MateriaBean materiaBean;
    
    private List<Materia> materiasDisponibles;
    /**
     * Creates a new instance of DocenteMateriaFormBean
     */
    public DocenteMateriaFormBean() {
        materiasDisponibles = new ArrayList<>();
    }
    
    /**Metodo que permite asignar una materia a un docente, 
     * antes verifica si esa materia ya fue asignada a ese docente*/
    public void agregarDocenteMateria() {
        docenteMateriaBean.getDocenteMateria().setMateria(materiaBean.getMateria());
        docenteMateriaBean.getDocenteMateria().setDocente(docenteBean.getDocente());
        docenteMateriaBean.getDocenteMateria().setHabilitado(true);
        docenteMateriaBean.getDocenteMateria().setEstado(true);
        if( validarAsignacionMateria() == true){
            docenteMateriaBean.agregarDocenteMateria();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Materia asignada a docente correctamente", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            docenteMateriaBean.setDocenteMateria(new DocenteMateria());
            docenteMateriaBean.getDocenteMateria().setAnio(0);
            carreraBean.setCarrera(new Carrera());
            materiaBean.setMateria(new Materia());
            docenteBean.setDocente(new Docente());
        } else{
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El docente ya tiene asignada esa materia", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
     
    }
    
    /**Permite verificar si la materia ya fue asignada al docente*/
    public boolean validarAsignacionMateria(){
        return docenteMateriaBean.validarAsignacionMateria() == null;
    }
    
    /**Permite modificar la asignacion de la materia al docente*/
    public void modificarDocenteMateria() {
        docenteMateriaBean.modificarDocenteMateria();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignacion de Materia modificado", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        docenteMateriaBean.setDocenteMateria(new DocenteMateria());
        RequestContext.getCurrentInstance().execute("PF('dialogEditar').hide();");    
    }
    
    
    /**Permite eliminar la asignacion de materia al docente*/
    public void eliminarDocenteMateria() {
        docenteMateriaBean.getDocenteMateria().setHabilitado(false);
        docenteMateriaBean.eliminarDocenteMateria();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Asignacion de materia eliminada", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);   
            RequestContext.getCurrentInstance().execute("PF('dialogEliminar').hide();");
    }
    
    /**Metodo que permite obtener todas las materias que fueron asignadas*/
    public List<DocenteMateria> obtenerMateriasAsignadas(){
        return docenteMateriaBean.obtenerMateriasAsignadas();
    }
    
    /**Metodo que permite buscar las materias que pertenecen a la carrera seleccionada*/
    public void buscarMateria(){
        materiasDisponibles = new ArrayList<>();
        for(Materia mat : materiaBean.obtenerMaterias()){
            if(mat.getCarrera().getCodigo() == carreraBean.getCarrera().getCodigo()){
                materiasDisponibles.add(mat);
            }
        }
    }
    
    /**Metodo que permite obtener las materias por carrera*/
    public List<Materia> obtenerMateriaPorCarrera(){
        materiaBean.setMateria(docenteMateriaBean.getDocenteMateria().getMateria());
        return materiaBean.obtenerMateriasPorCarrera();
    }

    /**
     *  Método que permite exportar el archivo pdf, una lista de docenteMateria
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     */
    public void exportarDocenteMateriaPdf(ActionEvent actionEvent) throws JRException, IOException{
        Map<String, Object> parametros = new HashMap<String, Object>();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/docenteMateriaReport.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(obtenerMateriasAsignadas()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=reportDocenteMateria.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete(); 
    }
    
    /**
     * @return the docenteMateriaBean*/
    public DocenteMateriaBean getDocenteMateriaBean() {
        return docenteMateriaBean;
    }

    /**
     * @param docenteMateriaBean the docenteMateriaBean to set*/
    public void setDocenteMateriaBean(DocenteMateriaBean docenteMateriaBean) {
        this.docenteMateriaBean = docenteMateriaBean;
    }

    /**
     * @return the docenteBean
     */
    public DocenteBean getDocenteBean() {
        return docenteBean;
    }

    /**
     * @param docenteBean the docenteBean to set
     */
    public void setDocenteBean(DocenteBean docenteBean) {
        this.docenteBean = docenteBean;
    }

    /**
     * @return the materiaBean
     */
    public MateriaBean getMateriaBean() {
        return materiaBean;
    }

    /**
     * @param materiaBean the materiaBean to set
     */
    public void setMateriaBean(MateriaBean materiaBean) {
        this.materiaBean = materiaBean;
    }

    /**
     * @return the carreraBean
     */
    public CarreraBean getCarreraBean() {
        return carreraBean;
    }

    /**
     * @param carreraBean the carreraBean to set
     */
    public void setCarreraBean(CarreraBean carreraBean) {
        this.carreraBean = carreraBean;
    }

    /**
     * @return the materiasDisponibles
     */
    public List<Materia> getMateriasDisponibles() {
        return materiasDisponibles;
    }

    /**
     * @param materiasDisponibles the materiasDisponibles to set
     */
    public void setMateriasDisponibles(List<Materia> materiasDisponibles) {
        this.materiasDisponibles = materiasDisponibles;
    }
}
