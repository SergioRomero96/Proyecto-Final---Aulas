/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import aplicacion.datos.hibernate.dao.IPerfilDAO;
import aplicacion.datos.hibernate.dao.imp.PerfilDAOImp;
import aplicacion.modelo.dominio.Perfil;
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
public class PerfilBean implements Serializable {

    private Perfil perfil;

    /**
     * Creates a new instance of PerfilBean
     */
    public PerfilBean() {

    }

    @PostConstruct
    public void init() {
        perfil = new Perfil();
        perfil.setUsuario(new Usuario());
    }

    /**
     * Metodo que permite agregar el perfil
     * @param perfil
     */
    public void agregarPerfil(Perfil perfil) {
        IPerfilDAO perfilDAO = new PerfilDAOImp();
        perfilDAO.agregar(perfil);

    }

    /**
     * Metodo que permite modificar el perfil
     */
    public void modificarPerfil() {
        IPerfilDAO perfilDAO = new PerfilDAOImp();
        perfilDAO.modificar(perfil);
    }

    /**
     * @return the perfil
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

}
