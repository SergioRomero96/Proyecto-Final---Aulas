/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.PerfilBean;
import aplicacion.datos.hibernate.dao.IPerfilDAO;
import aplicacion.datos.hibernate.dao.imp.PerfilDAOImp;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Usuario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Sergio Romero
 */
@ManagedBean
@ViewScoped
public class PerfilFormBean {

    @ManagedProperty(value = "#{perfilBean}")
    private PerfilBean perfilBean;
//    private Perfil perfilNuevo;
//    private Usuario usuario;

    /**
     * Creates a new instance of PerfilFormBean
     */
    public PerfilFormBean() {
        
//        perfilNuevo = new Perfil();
//        usuario = new Usuario();
    }

    public Perfil getSupervisorSesion(){
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValidado");
        IPerfilDAO perfilDAO = new PerfilDAOImp();
        return perfilDAO.obtenerPerfil(usuario);
    }
    
//    public void registrarNuevoUsuarioPerfil() {
//        usuario.setTipoUsuario("Final");
//        usuario.setEstado(true);
//        perfilNuevo.setUsuario(usuario);
//        IPerfilDAO perfilDAO = new PerfilDAOImp();
//        if (perfilDAO.obtenerPerfil(usuario) == null) {
//            perfilBean.agregarPerfil(perfilNuevo);
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Agregado", null);
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//            perfilNuevo = new Perfil();
//            usuario = new Usuario();
//        } else {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ese Usuario ya existe!!", null);
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//        }
//
//    }
    
    public void actualizarPerfil() {
        getPerfilBean().modificarPerfil();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil actualizado", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    /**
     * @return the perfilBean
     */
    public PerfilBean getPerfilBean() {
        return perfilBean;
    }

    /**
     * @param perfilBean the perfilBean to set
     */
    public void setPerfilBean(PerfilBean perfilBean) {
        this.perfilBean = perfilBean;
    }

//    /**
//     * @return the perfilNuevo
//     */
//    public Perfil getPerfilNuevo() {
//        return perfilNuevo;
//    }
//
//    /**
//     * @param perfilNuevo the perfilNuevo to set
//     */
//    public void setPerfilNuevo(Perfil perfilNuevo) {
//        this.perfilNuevo = perfilNuevo;
//    }
//
//    /**
//     * @return the usuario
//     */
//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    /**
//     * @param usuario the usuario to set
//     */
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }

}
