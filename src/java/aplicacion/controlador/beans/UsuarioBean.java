/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.datos.hibernate.dao.IUsuarioDAO;
import aplicacion.datos.hibernate.dao.imp.UsuarioDAOImp;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Sergio Romero
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{
    private Usuario usuario;
    
    
    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }
    
    /**
     * Metodo que devuelve el usuario encontrado en la base de datos
     * @param nombreUsuario
     * @param password
     * @return  null si no lo encuentra*/
    public Usuario validarUsuario(String nombreUsuario, String password){
        IUsuarioDAO usuarioDAO = new UsuarioDAOImp();
        return usuarioDAO.validarUsuario(nombreUsuario, password);
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
