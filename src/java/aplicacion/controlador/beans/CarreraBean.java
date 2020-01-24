/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.datos.hibernate.dao.ICarreraDAO;
import aplicacion.datos.hibernate.dao.imp.CarreraDAOImp;
import aplicacion.modelo.dominio.Carrera;
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
public class CarreraBean implements Serializable {
    private Carrera carrera;
    private ICarreraDAO carreraDAO;

    /**
     * Creates a new instance of CarreraBean
     */
    public CarreraBean() {
    }
    
    @PostConstruct
    public void init() {
        carrera = new Carrera();
    }
    
    /**
     * Método que verifica si existe la carrera
     * @return null si la carrera no existe
     */
    public Carrera validarCarrera() {
        carreraDAO = new CarreraDAOImp();
        return carreraDAO.validarCarrera(carrera);
    }
    
    /**
     * Método que permite agregar una carrera en la tabla carreras
     */
    public void agregarCarrera() {
        carreraDAO = new CarreraDAOImp();
        carreraDAO.agregarCarrera(carrera);
    }
    
    /**
     * Método que permite modificar una carrera
     */
    public void modificarCarrera() {
        carreraDAO = new CarreraDAOImp();
        carreraDAO.modificarCarrera(carrera);
    }
    
    /**
     * Método que permite borrar (lógico) una carrera
     */
    public void eliminarCarrera() {
        carreraDAO = new CarreraDAOImp();
        carreraDAO.eliminarCarrera(carrera);
        carrera = new Carrera();
    }
    
    /**
     * Método que permite obtener carreras de la tabla carreras
     * @return una lista de carreras
     */ 
    public List<Carrera> obtenerCarreras() {
        carreraDAO = new CarreraDAOImp();
        return carreraDAO.obtenerCarreras();
    }
    
    /**
     *
     * @return the carrera
     */
    public Carrera getCarrera() {
        return carrera;
    }

    /**
     *
     * @param carrera the carrera to set
     */
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    /**
     *
     * @return the carreraDAO
     */
    public ICarreraDAO getCarreraDAO() {
        return carreraDAO;
    }

    /**
     *
     * @param carreraDAO the carreraDAO to set
     */
    public void setCarreraDAO(ICarreraDAO carreraDAO) {
        this.carreraDAO = carreraDAO;
    }
    
}
