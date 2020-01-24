/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.datos.hibernate.dao.IAsistenciaDAO;
import aplicacion.datos.hibernate.dao.imp.AsistenciaDAOImp;
import aplicacion.modelo.dominio.Asistencia;
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
public class AsistenciaBean implements Serializable {
    private Asistencia asistencia;
    private IAsistenciaDAO asistenciaDAO;

    /**
     * Creates a new instance of AsistenciaBean
     */
    public AsistenciaBean() {
    }
    
    @PostConstruct
    public void init() {
        asistencia = new Asistencia();
    }
    
    public void agregarAsistencia() {
        asistenciaDAO = new AsistenciaDAOImp();
        asistenciaDAO.agregar(asistencia);
    }
    
    public void modificarAsistencia() {
        asistenciaDAO = new AsistenciaDAOImp();
        asistenciaDAO.modificar(asistencia);
    }

    public List<Asistencia> obtenerAsistencia() {
        asistenciaDAO = new AsistenciaDAOImp();
        return asistenciaDAO.obtenerAsistencia();
    }
    
    public Asistencia validarAsistencia() {
        asistenciaDAO = new AsistenciaDAOImp();
        return asistenciaDAO.validarAsistencia(asistencia);
    }
    
    public List<Asistencia> obtenerAsistenciaPorMateria(DocenteMateria docenteMateria) {
        asistenciaDAO = new AsistenciaDAOImp();
        return asistenciaDAO.obtenerAsistenciaPorMateria(docenteMateria);
    }
    
    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public IAsistenciaDAO getAsistenciaDAO() {
        return asistenciaDAO;
    }

    public void setAsistenciaDAO(IAsistenciaDAO asistenciaDAO) {
        this.asistenciaDAO = asistenciaDAO;
    }
    
}
