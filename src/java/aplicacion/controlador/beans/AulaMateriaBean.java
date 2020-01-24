/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.datos.hibernate.dao.IAulaMateriaDAO;
import aplicacion.datos.hibernate.dao.imp.AulaMateriaDAOImp;
import aplicacion.modelo.dominio.AulaMateria;
import aplicacion.modelo.dominio.DocenteMateria;
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
public class AulaMateriaBean implements Serializable {
    private AulaMateria aulaMateria;
    private IAulaMateriaDAO aulaMateriaDAO;
    /**
     * Creates a new instance of AulaMateriaBean
     */
    public AulaMateriaBean() {
    }
    
    @PostConstruct
    public void init() {
        aulaMateria = new AulaMateria();
    }
    
    /**
     * Método que permite agregar un AulaMateria en la tabla AulasMaterias
     */
    public void agregarAulaMateria() {
        aulaMateriaDAO = new AulaMateriaDAOImp();
        aulaMateriaDAO.agregar(aulaMateria);
    }
    
    /**
     * Método que permite modificar un AulaMateria
     */
    public void modificarAulaMateria() {
        aulaMateriaDAO = new AulaMateriaDAOImp();
        aulaMateriaDAO.modificar(aulaMateria);
    }
    
    /**
     * Método que permite borrar (lógico) un AulaMateria
     */
    public void eliminarAulaMateria() {
        aulaMateriaDAO = new AulaMateriaDAOImp();
        aulaMateriaDAO.eliminar(aulaMateria);
        aulaMateria = new AulaMateria();
    }
    
    /**
     * Método que verifica si ya existe el AulaMateria
     * @return null si el AulaMateria no existe
     */
    public AulaMateria validarAulaMateria() {
        aulaMateriaDAO = new AulaMateriaDAOImp();
        return aulaMateriaDAO.validarAulaMateria(aulaMateria);
    }
    
    /**
     * Método que permite obtener AulasMaterias de la tabla AulasMaterias
     * @return una lista de AulasMaterias
     */ 
    public List<AulaMateria> obtenerAulaMateria() {
        aulaMateriaDAO = new AulaMateriaDAOImp();
        return aulaMateriaDAO.obtenerAulaMateria();
    }
    
   

    /**
     *
     * @return the aulaMateria
     */
    public AulaMateria getAulaMateria() {
        return aulaMateria;
    }

    /**
     *
     * @param aulaMateria the aulaMateria to set
     */
    public void setAulaMateria(AulaMateria aulaMateria) {
        this.aulaMateria = aulaMateria;
    }

    /**
     *
     * @return the aulaMateriaDAO
     */
    public IAulaMateriaDAO getAulaMateriaDAO() {
        return aulaMateriaDAO;
    }

    /**
     *
     * @param aulaMateriaDAO the aulaMateriaDAO to set
     */
    public void setAulaMateriaDAO(IAulaMateriaDAO aulaMateriaDAO) {
        this.aulaMateriaDAO = aulaMateriaDAO;
    }
    
}
