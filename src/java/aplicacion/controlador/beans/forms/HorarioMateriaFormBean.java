/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.AulaMateriaBean;
import aplicacion.controlador.beans.InscripcionAlumnoBean;
import aplicacion.datos.hibernate.dao.IAlumnoDAO;
import aplicacion.datos.hibernate.dao.imp.AlumnoDAOImp;
import aplicacion.modelo.dominio.Alumno;
import aplicacion.modelo.dominio.AulaMateria;
import aplicacion.modelo.dominio.InscripcionAlumno;
import aplicacion.modelo.dominio.Perfil;
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

/**
 *
 * @author Sergio Romero
 */
@ManagedBean
@ViewScoped
public class HorarioMateriaFormBean implements Serializable {

    @ManagedProperty(value = "#{inscripcionAlumnoBean}")
    private InscripcionAlumnoBean inscripcionAlumnoBean;

    @ManagedProperty(value = "#{aulaMateriaBean}")
    private AulaMateriaBean aulaMateriaBean;

    private List<AulaMateria> horariosAImprimir;

    private boolean boton;
    /**
     * Creates a new instance of HorarioFormBean
     */
    public HorarioMateriaFormBean() {
        horariosAImprimir = new ArrayList<>();
        boton = true;
    }

    /**
     *  Método que permite exportar el archivo pdf, una lista de horarioMateria
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     */            
    public void exportarHorarioCursadaPdf(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportHorarioCursada.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(horariosAImprimir));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=reportHorarioCursada.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
    /**
     * Metodo que permite obtener la lista de materias que cursa el alumno
     * @return las materias que cursa el alumno
     */
    public List<InscripcionAlumno> obtenerMateriasAlumno() {
        List<InscripcionAlumno> inscripcionAlumnos = new ArrayList<>();
        for (InscripcionAlumno ia : inscripcionAlumnoBean.obtenerInscripcionAlumnos()) {
            if (ia.getAlumno().getCodigo() == getAlumnoSesion().getCodigo()) {
                inscripcionAlumnos.add(ia);
            }
        }
        return inscripcionAlumnos;
    }

    /**
     * Metodo que permite obtener los horarios de las materias que cursa el alumno
     * @return los horarios de las materias que se inscribio el alumno
     */
    public List<AulaMateria> obtenerHorarioMaterias() {
        List<AulaMateria> aulaMaterias = new ArrayList<>();
        for (InscripcionAlumno ia : obtenerMateriasAlumno()) {
            for (AulaMateria am : aulaMateriaBean.obtenerAulaMateria()) {
                if (ia.getDocenteMateria().getMateria().getCodigo() == am.getDocenteMateria().getMateria().getCodigo()) {
                    aulaMaterias.add(am);
                }
            }
        }
        return aulaMaterias;
    }

    /**
     * Metodo que permite agregar un horario elegido por el alumno Para poder
     * imprimir su horario de cursada
     * @param aulaMateria
     */
    public void agregarHorarioParaImprimir(AulaMateria aulaMateria) {
        if (verificarHorarioElegido(aulaMateria)) {
            horariosAImprimir.add(aulaMateria);
            boton = false;
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Horario agregado correctamente para imprimir", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Horario ya fue  agregado para imprimir", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    /**
     * Metodo que permite eliminar el horario elegido por el alumno
     * @param aulaMateria
     */
    public void eliminarHorarioParaImprimir(AulaMateria aulaMateria) {
        if (!verificarHorarioElegido(aulaMateria)) {
            horariosAImprimir.remove(obtenerHorario(aulaMateria));           
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Horario elegido para imprimir, eliminado correctamente ", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);       
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Horario no fue agregado para imprimir", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        if (horariosAImprimir.isEmpty()) {
            boton = true;
        }
    }

    /**
     * Metodo que verifica si el horario ya fue elegido por el usuario
     * @param aulaMateria
     * @return true si el horario no fue elegido
     */
    public boolean verificarHorarioElegido(AulaMateria aulaMateria) {
        boolean encontrado = true;
        if (horariosAImprimir.isEmpty()) {           
            return encontrado;
        } else {
            for (AulaMateria am : horariosAImprimir) {
                if (am.getCodigo() == aulaMateria.getCodigo()) {
                    encontrado = false;
                    break;
                }
            }
        }
        return encontrado;
    }

    /**
     * Metodo que permite obtener el horario
     * @param aulaMateria
     * @return el horario
     */
    public AulaMateria obtenerHorario(AulaMateria aulaMateria) {
        AulaMateria amAux = null;
        for (AulaMateria am : horariosAImprimir) {
            if (am.getCodigo() == aulaMateria.getCodigo()) {
                amAux = am;
                break;
            }
        }
        return amAux;
    }

    /**
     * Metodo que permite obtener el alumno en sesion
     * @return el alumno en sesion
     */
    public Alumno getAlumnoSesion() {
        Perfil perfil = (Perfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfilValidado");
        IAlumnoDAO alumnoDAO = new AlumnoDAOImp();
        return alumnoDAO.obtenerAlumnoPorPerfil(perfil);
    }

    /**
     * @return the inscripcionAlumnoBean
     */
    public InscripcionAlumnoBean getInscripcionAlumnoBean() {
        return inscripcionAlumnoBean;
    }

    /**
     * @param inscripcionAlumnoBean the inscripcionAlumnoBean to set
     */
    public void setInscripcionAlumnoBean(InscripcionAlumnoBean inscripcionAlumnoBean) {
        this.inscripcionAlumnoBean = inscripcionAlumnoBean;
    }

    /**
     * @return the aulaMateriaBean
     */
    public AulaMateriaBean getAulaMateriaBean() {
        return aulaMateriaBean;
    }

    /**
     * @param aulaMateriaBean the aulaMateriaBean to set
     */
    public void setAulaMateriaBean(AulaMateriaBean aulaMateriaBean) {
        this.aulaMateriaBean = aulaMateriaBean;
    }

    /**
     * @return the horariosAImprimir
     */
    public List<AulaMateria> getHorariosAImprimir() {
        return horariosAImprimir;
    }

    /**
     * @param horariosAImprimir the horariosAImprimir to set
     */
    public void setHorariosAImprimir(List<AulaMateria> horariosAImprimir) {
        this.horariosAImprimir = horariosAImprimir;
    }

    /**
     * @return the boton
     */
    public boolean isBoton() {
        return boton;
    }

    /**
     * @param boton the boton to set
     */
    public void setBoton(boolean boton) {
        this.boton = boton;
    }

}
