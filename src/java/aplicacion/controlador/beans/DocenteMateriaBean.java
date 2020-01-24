/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.datos.hibernate.dao.IDocenteMateriaDAO;
import aplicacion.datos.hibernate.dao.imp.DocenteMateriaDAOImp;
import aplicacion.modelo.dominio.DocenteMateria;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Romero Sergio
 */
@ManagedBean
@ViewScoped
public class DocenteMateriaBean implements Serializable {

    private DocenteMateria docenteMateria;
    private IDocenteMateriaDAO docenteMateriaDAO;

    /**
     * Creates a new instance of DocenteMateriaBean
     */
    public DocenteMateriaBean() {
    }

    @PostConstruct
    public void init() {
        docenteMateria = new DocenteMateria();
    }

    /**
     * Metodo que permite guardar el docente y la materia que se le asigno
     */
    public void agregarDocenteMateria() {
        docenteMateriaDAO = new DocenteMateriaDAOImp();
        docenteMateriaDAO.agregar(docenteMateria);
    }

    /**
     * Metodo que permite modificar al docente que se le asigno la materia
     */
    public void modificarDocenteMateria() {
        docenteMateriaDAO = new DocenteMateriaDAOImp();
        docenteMateriaDAO.modificar(docenteMateria);
    }

    /**
     * Metodo que permite eliminar la asignacion de la materia al docente
     */
    public void eliminarDocenteMateria() {
        docenteMateriaDAO = new DocenteMateriaDAOImp();
        docenteMateriaDAO.eliminar(docenteMateria);
        docenteMateria = new DocenteMateria();
    }

    /**
     * Metodo que verifica si la materia ya fue asignada al docente
     */
    public DocenteMateria validarAsignacionMateria() {
        docenteMateriaDAO = new DocenteMateriaDAOImp();
        return docenteMateriaDAO.validarAsignacionMateria(docenteMateria);
    }

    /**
     * Metodo que permite obtener las materias asignadas a los docentes
     */
    public List<DocenteMateria> obtenerMateriasAsignadas() {
        docenteMateriaDAO = new DocenteMateriaDAOImp();
        return docenteMateriaDAO.obtenerMateriasAsignadas();
    }

    /**
     * @return the docenteMateriaDAO
     */
    public IDocenteMateriaDAO getDocenteMateriaDAO() {
        return docenteMateriaDAO;
    }

    /**
     * @param docenteMateriaDAO the docenteMateriaDAO to set
     */
    public void setDocenteMateriaDAO(IDocenteMateriaDAO docenteMateriaDAO) {
        this.docenteMateriaDAO = docenteMateriaDAO;
    }

    /**
     * @return the docenteMateria
     */
    public DocenteMateria getDocenteMateria() {
        return docenteMateria;
    }

    /**
     * @param docenteMateria the docenteMateria to set
     */
    public void setDocenteMateria(DocenteMateria docenteMateria) {
        this.docenteMateria = docenteMateria;
    }
}
