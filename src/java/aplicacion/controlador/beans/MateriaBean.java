/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.datos.hibernate.dao.IMateriaDAO;
import aplicacion.datos.hibernate.dao.imp.MateriaDAOImp;
import aplicacion.modelo.dominio.Materia;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author GASTON TOCONAS
 */
@ManagedBean
@ViewScoped
public class MateriaBean implements Serializable {
    private Materia materia;
    private IMateriaDAO materiaDAO;
    /**
     * Creates a new instance of MateriaBean
     */
    public MateriaBean() {
    }
    
    @PostConstruct
    public void init() {
        materia = new Materia();
    }
    
    /**
     * Método que verifica si existe la materia y su carrera relacionada
     * @return null si la materia junto a su carrera relacionada no existe
     */
    public Materia validarMateria() {
        materiaDAO = new MateriaDAOImp();
        return materiaDAO.validarMateria(materia);
    }
    
    /**
     * Método que permite agregar una materia en la tabla materias
     */
    public void agregarMateria() {
        materiaDAO = new MateriaDAOImp();
        materiaDAO.agregar(materia);
    }
    
    /**
     * Método que permite modificar una materia
     */
    public void modificarMateria() {
        materiaDAO = new MateriaDAOImp();
        materiaDAO.modificar(materia);
    }
    
    /**
     * Método que permite borrar (lógico) una materia
     */
    public void eliminarMateria() {
        materiaDAO = new MateriaDAOImp();
        materiaDAO.eliminar(materia);
        materia = new Materia();
    }
    
    /**
     * Método que permite obtener materias de la tabla materias
     * @return una lista de materias
     */ 
    public List<Materia> obtenerMaterias() {
        materiaDAO = new MateriaDAOImp();
        return materiaDAO.obtenerMaterias();
    }

    /**
     * Método que permite obtener materias de acuerdo a su carrera relacionada
     * @return una lista de materias de acuerdo a su carrera relacionada
     */ 
    public List<Materia> obtenerMateriasPorCarrera() {
        materiaDAO = new MateriaDAOImp();
        return materiaDAO.obtenerMateriasPorCarrera(materia.getCarrera());
    }
    
    /**
     *
     * @return the materia
     */
    public Materia getMateria() {
        return materia;
    }

    /**
     *
     * @param materia the materia to set
     */
    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    /**
     *
     * @return the materiaDAO
     */
    public IMateriaDAO getMateriaDAO() {
        return materiaDAO;
    }

    /**
     *
     * @param materiaDAO the materiaDAO to set
     */
    public void setMateriaDAO(IMateriaDAO materiaDAO) {
        this.materiaDAO = materiaDAO;
    }
}
