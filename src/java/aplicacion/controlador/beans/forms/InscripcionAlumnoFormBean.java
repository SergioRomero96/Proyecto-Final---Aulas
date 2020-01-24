/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.DocenteMateriaBean;
import aplicacion.controlador.beans.InscripcionAlumnoBean;
import aplicacion.datos.hibernate.dao.IAlumnoDAO;
import aplicacion.datos.hibernate.dao.IDocenteDAO;
import aplicacion.datos.hibernate.dao.imp.AlumnoDAOImp;
import aplicacion.datos.hibernate.dao.imp.DocenteDAOImp;
import aplicacion.modelo.dominio.Alumno;
import aplicacion.modelo.dominio.Docente;
import aplicacion.modelo.dominio.DocenteMateria;
import aplicacion.modelo.dominio.InscripcionAlumno;
import aplicacion.modelo.dominio.Perfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Sergio Romero
 */
@ManagedBean
@ViewScoped
public class InscripcionAlumnoFormBean implements Serializable {

    @ManagedProperty(value = "#{inscripcionAlumnoBean}")
    private InscripcionAlumnoBean inscripcionAlumnoBean;

    @ManagedProperty(value = "#{docenteMateriaBean}")
    private DocenteMateriaBean docenteMateriaBean;

    /**
     * Creates a new instance of InscripcionAlumnoFormBean
     */
    public InscripcionAlumnoFormBean() {
    }

    /**
     * Metodo que permite obtener el alumno en sesion
     */
    public Alumno getAlumnoSesion() {
        Perfil perfil = (Perfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfilValidado");
        IAlumnoDAO alumnoDAO = new AlumnoDAOImp();
        return alumnoDAO.obtenerAlumnoPorPerfil(perfil);
    }

    /**
     * Metodo que permite obtener las materias que puede cursar el alumno
     */
    public List<DocenteMateria> obtenerMateriasDocente() {
        List<DocenteMateria> materiasAlumno = new ArrayList<>();
        for (DocenteMateria dm : docenteMateriaBean.obtenerMateriasAsignadas()) {
            if (dm.getMateria().getCarrera().equals(getAlumnoSesion().getCarrera())) {
                materiasAlumno.add(dm);
            }
        }
        return materiasAlumno;
    }

    /**
     * Metodo que permite inscribir el alumno a una materia, antes de agregarlo
     * se verifica si el alumno ya se inscribio a esa materia
     */
    public void inscribirMateriaAlumno(DocenteMateria docenteMateria) {
        inscripcionAlumnoBean.getInscripcionAlumno().setDocenteMateria(docenteMateria);
        inscripcionAlumnoBean.getInscripcionAlumno().setAlumno(getAlumnoSesion());
        inscripcionAlumnoBean.getInscripcionAlumno().setEstado(true);
        if (validarInscripcion() == true) {
            inscripcionAlumnoBean.agregar();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Inscripcion realizada correctamente", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya estas inscripto en esa materia", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    /**
     * Metodo que verifica la inscripcion del alumno
     * @return false si el alumno no se inscribio a la materia
     */
    public boolean validarInscripcion() {
        return inscripcionAlumnoBean.validarInscripcion() == null;
    }

    /**
     * Metodo que permite obtener todas las inscripciones de los alumnos
     * @return la lista de alumnos inscriptos
     */
    public List<InscripcionAlumno> obtenerInscripcionAlumnos() {
        return inscripcionAlumnoBean.obtenerInscripcionAlumnos();
    }

    /**
     * Metodo que permite obtener el docente que esta en sesion
     * @return el docente en sesion
     */
    public Docente getDocenteSesion() {
        Perfil perfil = (Perfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfilValidado");
        IDocenteDAO docenteDAO = new DocenteDAOImp();
        return docenteDAO.obtenerDocentePorPerfil(perfil);
    }

    /**
     * Metodo que permite obtener los alumnos inscripto,
     * en las materia que dicta el docente
     * 
     * @return la lista de alumnos inscriptos en la materia que dicta el docente
     */
    public List<InscripcionAlumno> obtenerInscripcionAlumnosPorDocente() {
        List<InscripcionAlumno> inscripcionAlumnos = new ArrayList<>();
        for (InscripcionAlumno ia : inscripcionAlumnoBean.obtenerInscripcionAlumnos()) {
            if (ia.getDocenteMateria().getDocente().getCodigo() == getDocenteSesion().getCodigo()) {
                inscripcionAlumnos.add(ia);
            }
        }
        return inscripcionAlumnos;
    }

    /**
     * Metodo que permite borrar la inscripcion realizada
     */
    public void borrarInscripcion() {
        inscripcionAlumnoBean.borrar();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baja de inscripcion realizada correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('dialogEliminar').hide();");
    }

    /**
     * @return the docenteMateriaBean
     */
    public DocenteMateriaBean getDocenteMateriaBean() {
        return docenteMateriaBean;
    }

    /**
     * @param docenteMateriaBean the docenteMateriaBean to set
     */
    public void setDocenteMateriaBean(DocenteMateriaBean docenteMateriaBean) {
        this.docenteMateriaBean = docenteMateriaBean;
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

}
