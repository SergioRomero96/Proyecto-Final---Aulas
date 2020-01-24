/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.modelo.test;

import aplicacion.datos.hibernate.dao.IPerfilDAO;
import aplicacion.datos.hibernate.dao.imp.PerfilDAOImp;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Usuario;
import java.util.Date;

/**
 *
 * @author Sergio Romero
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Usuario usuario = new Usuario("horac", "1234", "Administrativo", true);
        Perfil perfil = new Perfil(usuario, "horacio", "Argañaraz", "22000000", new Date(), "horacio@gmail.com", "38884334422");
        IPerfilDAO perfilDAO = new PerfilDAOImp();
        if(perfilDAO.obtenerPerfil(usuario) != null)
            System.out.println("ese usuario ya existe...");
        else{
            perfilDAO.agregar(perfil);
        }
        
    }
    
}
