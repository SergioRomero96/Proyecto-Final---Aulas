/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.datos.hibernate.dao.IDocenteDAO;
import aplicacion.datos.hibernate.dao.IPerfilDAO;
import aplicacion.datos.hibernate.dao.imp.DocenteDAOImp;
import aplicacion.datos.hibernate.dao.imp.PerfilDAOImp;
import aplicacion.modelo.dominio.Docente;
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
public class DocenteBean implements Serializable {

    private Docente docente;
    private IDocenteDAO docenteDAO;

    /**
     * Creates a new instance of DocenteBean
     */
    public DocenteBean() {
    }

    @PostConstruct
    public void init() {
        docente = new Docente();
        obtenerPerfil();
        if (docente.getPerfil() == null) {
            docente.setPerfil(new Perfil());
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("perfilValidado", docente.getPerfil());
        }
    }

    /**
     * Metodo que permite modificar el perfil del docente
     */
    public void modificarPerfil() {
        IPerfilDAO perfilDAO = new PerfilDAOImp();
        perfilDAO.modificar(docente.getPerfil());
    }

    /**
     * Metodo que permite obtener el perfil, a partir del usuario que se guardo
     * en el sessionMap
     */
    public void obtenerPerfil() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValidado");
        IPerfilDAO perfilDAO = new PerfilDAOImp();
        docente.setPerfil(perfilDAO.obtenerPerfil(usuario));
    }

    /**
     * Metodo que verifica si el legajo ya fue asignado
     * @return null si el legajo no existe
     */
    public Docente validarLegajo() {
        docenteDAO = new DocenteDAOImp();
        return docenteDAO.validarLegajoDocente(docente);
    }

    /**
     * Metodo que permite guardar el docente en la tabla docente
     */
    public void guardarDocente() {
        docenteDAO = new DocenteDAOImp();
        docenteDAO.agregar(docente);
    }

    /**
     * Metodo que permite modificar los datos del docente
     */
    public void modificarDocente() {
        docenteDAO = new DocenteDAOImp();
        docenteDAO.modificar(docente);
    }

    /**
     * Metodo que permite eliminar el docente(borrado logico)
     */
    public void eliminarDocente() {
        docenteDAO = new DocenteDAOImp();
        docenteDAO.eliminar(docente);
        docente = new Docente();
    }

    /**
     * Metodo que permite obtener los docentes de la tabla docentes
     * @return la lista de docentes
     */
    public List<Docente> obtenerDocentes() {
        docenteDAO = new DocenteDAOImp();
        return docenteDAO.obtenerDocentes();
    }

    /**
     * @return the docente
     */
    public Docente getDocente() {
        return docente;
    }

    /**
     * @param docente the docente to set
     */
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    /**
     * @return the docenteDAO
     */
    public IDocenteDAO getDocenteDAO() {
        return docenteDAO;
    }

    /**
     * @param docenteDAO the docenteDAO to set
     */
    public void setDocenteDAO(IDocenteDAO docenteDAO) {
        this.docenteDAO = docenteDAO;
    }

}
