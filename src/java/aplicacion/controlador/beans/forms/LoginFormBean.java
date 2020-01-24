/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.UsuarioBean;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sergio Romero
 */
@ManagedBean
@ViewScoped
public class LoginFormBean implements Serializable {

    private String nombreUsuario;
    private String password;
    private String tipoUsuario;
    
    private boolean desactivar = true;
    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean;

    /**
     * Creates a new instance of LoginFormBean
     */
    public LoginFormBean() {
    }
    
    public void activarColumnasOpciones(){
        setDesactivar(true);
    }
    
    public void desactivarColumnasOpciones(){
        setDesactivar(false);
    }
    
    /**
     * metodo que verifica si el usuario existe en la base de datos.
     * Si existe retorna el string que hace referencia a la pagina del menu.
     * @return null si no existe y no le permite acceder al menu
     */
    public String validarUsuario() {
        String resultado = null;
        Usuario usuario = usuarioBean.validarUsuario(nombreUsuario, password);
        if (usuario != null) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido al Sistema.", "Bienvenido al Sistema.");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioValidado", usuario);
            resultado = "menu?faces-redirect=true";
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos erróneos. Por favor, inténtelo otra vez", "Por favor, inténtelo otra vez");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return resultado;
    }
    
    /**
     * @return el nombre del usuario que inicio sesion
     */
    public String getNombreUsuarioValidado() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValidado");
        return usuario.getNombreUsuario();
    }
    
    /**
     * metodo que devuelve una cadena que hace referencia a la pagina del login
     * @return la pagina del login 
     */
    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sesion Cerrada", "Sesion Cerrada");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        String resultado = "/index?faces-redirect=true";
        return resultado;
    }

    /**
     * Metodo que verifica que haya iniciado sesion
     * @return false si el usuario no se encuentra logueado
     */
    public boolean verificarSesion() {
        boolean sesionValida = false;
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValidado") != null) {
            sesionValida = true;
        }
        return sesionValida;
    }

    /**
     * Metodo que devuelve un entero que hace referencia al nivel de usuario
     * 1 - Administrativo
     * 2 - Supervisor
     * 3 - Usuario Final
     * @return un entero que hace referencia al tipo de usuario
     */
    public int verificarTipoUsuario() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioValidado");
        int resultado = 0;
        switch (usuario.getTipoUsuario()) {
            case "Administrativo":
                resultado = 1;
                break;
            case "Supervisor":
                resultado = 2;
                break;
            case "Final":
                resultado = 3;
                break;
        }
        return resultado;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the usuarioBean
     */
    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    /**
     * @param usuarioBean the usuarioBean to set
     */
    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    /**
     * @return the desactivar
     */
    public boolean isDesactivar() {
        return desactivar;
    }

    /**
     * @param desactivar the desactivar to set
     */
    public void setDesactivar(boolean desactivar) {
        this.desactivar = desactivar;
    }

}
