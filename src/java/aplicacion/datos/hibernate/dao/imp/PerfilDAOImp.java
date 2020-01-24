/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion.datos.hibernate.dao.imp;

import aplicacion.datos.hibernate.configuracion.HibernateUtil;
import aplicacion.datos.hibernate.dao.IPerfilDAO;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * @author Sergio Romero
 */
public class PerfilDAOImp implements IPerfilDAO, Serializable{

    @Override
    public Perfil obtenerPerfil(Usuario usuario) {
        Perfil perfil = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Perfil.class);
        criteria.createAlias("usuario", "u");
        criteria.add(Restrictions.like("u.nombreUsuario", usuario.getNombreUsuario()));
        if(!criteria.list().isEmpty()){
            perfil = (Perfil) criteria.list().get(0);
        }
        session.close();
        return perfil;
    }

    @Override
    public void agregar(Perfil perfil) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(perfil);
        session.getTransaction().commit();
        session.close();
        
    }

    @Override
    public void modificar(Perfil perfil) {
     Session session = HibernateUtil.getSessionFactory().openSession();
     session.beginTransaction();
     session.update(perfil);
     session.getTransaction().commit();
     session.close();
    }

}
