/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion.datos.hibernate.dao.imp;

import aplicacion.datos.hibernate.configuracion.HibernateUtil;
import aplicacion.datos.hibernate.dao.IUsuarioDAO;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * @author Sergio Romero
 */
public class UsuarioDAOImp implements IUsuarioDAO, Serializable{

    @Override
    public Usuario validarUsuario(String nombreUsuario, String password) {
        Usuario usuario = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.like("nombreUsuario", nombreUsuario));
        criteria.add(Restrictions.like("password", password));
        criteria.add(Restrictions.like("estado", true));
        if(!criteria.list().isEmpty())
            usuario = (Usuario)criteria.list().get(0);
        session.close();
        return usuario;
    }



}
