/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.datos.hibernate.dao.IAulaDAO;
import aplicacion.datos.hibernate.dao.imp.AulaDAOImp;
import aplicacion.modelo.dominio.Aula;
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
public class AulaBean implements Serializable {
    private Aula aula;
    private IAulaDAO aulaDAO;
    
    /**
     * Creates a new instance of AulaBean
     */
    public AulaBean() {
    }
    
    @PostConstruct
    public void init() {
        aula = new Aula();
    }
    
    /**
     * Método que verifica si ya existe la aula
     * @return null si la aula no existe
     */
    public Aula verificarAula() {
        aulaDAO = new AulaDAOImp();
        return aulaDAO.validarNombreAula(aula);
    }
    
    /**
     * Método que permite agregar un aula en la tabla aula
     */
    public void agregarAula() {
        aulaDAO = new AulaDAOImp();
        aulaDAO.agregarAula(aula);
    }

    /**
     * Método que permite modificar un aula
     */
    public void modificarAula() {
        aulaDAO = new AulaDAOImp();
        aulaDAO.modificarAula(aula);
    }

    /**
     * Método que permite borrar (lógico) un aula
     */
    public void eliminarAula() {
        aulaDAO = new AulaDAOImp();
        aulaDAO.eliminarAula(aula);
        aula = new Aula();
    }
    
    /**
     * Método que permite obtener aulas de la tabla aulas
     * @return una lista de aulas
     */ 
    public List<Aula> obtenerAulas() {
        aulaDAO = new AulaDAOImp();
        return aulaDAO.obtenerAulas();
    }
    
    /**
     *
     * @return the aula
     */
    public Aula getAula() {
        return aula;
    }

    /**
     *
     * @param aula the aula to set
     */
    public void setAula(Aula aula) {
        this.aula = aula;
    }

    /**
     *
     * @return the aulaDAO
     */
    public IAulaDAO getAulaDAO() {
        return aulaDAO;
    }

    /**
     *
     * @param aulaDAO the aulaDAO to set
     */
    public void setAulaDAO(IAulaDAO aulaDAO) {
        this.aulaDAO = aulaDAO;
    }

}
