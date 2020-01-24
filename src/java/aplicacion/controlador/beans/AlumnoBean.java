/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.datos.hibernate.dao.IAlumnoDAO;
import aplicacion.datos.hibernate.dao.IPerfilDAO;
import aplicacion.datos.hibernate.dao.imp.AlumnoDAOImp;
import aplicacion.datos.hibernate.dao.imp.PerfilDAOImp;
import aplicacion.modelo.dominio.Alumno;
import aplicacion.modelo.dominio.Carrera;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sergio Romero
 */
@ManagedBean
@ViewScoped
public class AlumnoBean implements Serializable {

    private Alumno alumno;

    /**
     * Creates a new instance of AlumnoBean
     */
    public AlumnoBean() {
    }

    @PostConstruct
    public void init() {
        alumno = new Alumno();
        obtenerPerfil();
        if (alumno.getPerfil() == null) {
            alumno.setPerfil(new Perfil());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("perfilValidado", alumno.getPerfil());
        }
    }

    /**
     * Metodo que permite obtener el perfil apartir del usuario que se guardo 
     * en el sesionMap
     */
    public void obtenerPerfil() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValidado") != null) {
            Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValidado");
            IPerfilDAO perfilDAO = new PerfilDAOImp();
            alumno.setPerfil(perfilDAO.obtenerPerfil(usuario));
        } else {
            alumno.setPerfil(null);
        }

    }

    /**
     * Metodo que permite modificar el perfil del alumno
     */
    public void modificarPerfil() {
        IPerfilDAO perfilDAO = new PerfilDAOImp();
        perfilDAO.modificar(alumno.getPerfil());
    }

    /**
     * Metodo que permite eliminar la cuenta de usuario
     */
    public void eliminarCuentaUsuario() {
        IAlumnoDAO alumnoDAO = new AlumnoDAOImp();
        alumnoDAO.eliminarUsuarioAlumno(alumno);
    }

    /**
     * Metodo que permite guardar un alumno en la tabla alumno
     */
    public void guardarAlumno() {
        IAlumnoDAO alumnoDAO = new AlumnoDAOImp();
        alumnoDAO.agregar(alumno);
    }

    /**
     * Metodo que permite obtener la lista de alumnos de la tabla Alumno
     * @return la lista de alumnos
     */
    public List<Alumno> obtenerAlumnos() {
        IAlumnoDAO alumnoDAO = new AlumnoDAOImp();
        return alumnoDAO.obtenerAlumnos();
    }

    /**
     * Metodo que verifica si la libreta universitaria ya fue asignada
     * @return null si la libreta universitaria no fue asignada
     */
    public Alumno validarLibretaUniversitaria() {
        IAlumnoDAO alumnoDAO = new AlumnoDAOImp();
        return alumnoDAO.validarLibretaUniversitaria(alumno);
    }

    /**
     * Metodo que permite modificar los datos academicos del alumno
     */
    public void modificarAlumno() {
        IAlumnoDAO alumnoDAO = new AlumnoDAOImp();
        alumnoDAO.modificar(alumno);
    }

    /**
     * @return the alumno
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

}
