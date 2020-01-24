/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.datos.hibernate.dao.InscripcionAlumnoDAO;
import aplicacion.datos.hibernate.dao.imp.InscripcionAlumnoDAOImp;
import aplicacion.modelo.dominio.InscripcionAlumno;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Sergio Romero
 */
@ManagedBean
@ViewScoped
public class InscripcionAlumnoBean implements Serializable {

    private InscripcionAlumno inscripcionAlumno;
    private InscripcionAlumnoDAO inscripcionAlumnoDAO;

    /**
     * Creates a new instance of InscripcionAlumnoBean
     */
    public InscripcionAlumnoBean() {
    }

    @PostConstruct
    public void init() {
        setInscripcionAlumno(new InscripcionAlumno());
        setInscripcionAlumnoDAO(new InscripcionAlumnoDAOImp());
    }

    /**
     * Metodo q verifica si el alumno ya se inscribio a la materia
     * @return null si el alumno no se inscribio a determinada materia
     */
    public InscripcionAlumno validarInscripcion() {
        inscripcionAlumnoDAO = new InscripcionAlumnoDAOImp();
        return inscripcionAlumnoDAO.validarIscripcion(inscripcionAlumno);
    }

    /**
     * Metodo que permite guardar la inscripcion del alumno
     */
    public void agregar() {
        inscripcionAlumnoDAO = new InscripcionAlumnoDAOImp();
        inscripcionAlumnoDAO.agregar(inscripcionAlumno);
    }

    /**
     * Metodo que permite borrar la inscripcion del alumno
     */
    public void borrar() {
        inscripcionAlumnoDAO = new InscripcionAlumnoDAOImp();
        inscripcionAlumnoDAO.borrar(inscripcionAlumno);
    }

    /**
     * Metodo que permite obtener la lista de inscripciones de los alumnos
     * @return la lista de inscripciones
     */
    public List<InscripcionAlumno> obtenerInscripcionAlumnos() {
        inscripcionAlumnoDAO = new InscripcionAlumnoDAOImp();
        return inscripcionAlumnoDAO.obtenerInscripcionAlumnos();
    }

    /**
     * @return the inscripcionAlumno
     */
    public InscripcionAlumno getInscripcionAlumno() {
        return inscripcionAlumno;
    }

    /**
     * @param inscripcionAlumno the inscripcionAlumno to set
     */
    public void setInscripcionAlumno(InscripcionAlumno inscripcionAlumno) {
        this.inscripcionAlumno = inscripcionAlumno;
    }

    /**
     * @return the inscripcionAlumnoDAO
     */
    public InscripcionAlumnoDAO getInscripcionAlumnoDAO() {
        return inscripcionAlumnoDAO;
    }

    /**
     * @param inscripcionAlumnoDAO the inscripcionAlumnoDAO to set
     */
    public void setInscripcionAlumnoDAO(InscripcionAlumnoDAO inscripcionAlumnoDAO) {
        this.inscripcionAlumnoDAO = inscripcionAlumnoDAO;
    }

}
