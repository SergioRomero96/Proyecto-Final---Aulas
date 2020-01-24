/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao;

import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Usuario;

/**
 *
 * @author Sergio Romero
 */
public interface IPerfilDAO {
    public Perfil obtenerPerfil(Usuario usuario);
    public void agregar(Perfil perfil);
    public void modificar(Perfil perfil);
}
