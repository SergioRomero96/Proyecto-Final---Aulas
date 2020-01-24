/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao.imp;

import aplicacion.datos.hibernate.configuracion.HibernateUtil;
import aplicacion.datos.hibernate.dao.IDocenteDAO;
import aplicacion.modelo.dominio.Docente;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sergio Romero
 */
public class DocenteDAOImp implements IDocenteDAO, Serializable {

    @Override
    public Docente obtenerDocentePorPerfil(Perfil perfil) {
        Docente docente = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Docente.class);
        criteria.createAlias("perfil", "per");
        criteria.add(Restrictions.like("per.dni", perfil.getDni()));
        if (!criteria.list().isEmpty()) {
            docente = (Docente) criteria.list().get(0);
        }
        session.close();
        return docente;
    }

 
    
    @Override
    public void agregar(Docente docente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(docente);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void modificar(Docente docente) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(docente);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void eliminar(Docente docente) {
        docente.setEstado(false);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(docente);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Docente> obtenerDocentes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Docente.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.createAlias("perfil", "p");
        criteria.addOrder(Order.asc("p.apellido"));
        criteria.addOrder(Order.asc("p.nombre"));
        List<Docente> docentes = new ArrayList<>();
        if (!criteria.list().isEmpty()) {
            docentes = criteria.list();
        }
        session.close();
        return docentes;
    }

    @Override
    public Docente validarLegajoDocente(Docente docente) {
        Docente docenteAux = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Docente.class);
        criteria.add(Restrictions.like("legajo", docente.getLegajo()));
        if (!criteria.list().isEmpty()) {
            docenteAux = (Docente) criteria.list().get(0);
        }
        session.close();
        return docenteAux;
    }

    @Override
    public Docente buscarDocente(Usuario usuario) {
        Docente docente = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Docente.class);
        criteria.createAlias("perfil", "per");
        criteria.add(Restrictions.like("per.usuario.nombreUsuario", usuario.getNombreUsuario()));
        if (!criteria.list().isEmpty()) {
            docente = (Docente) criteria.list().get(0);
        }
        session.close();
        return docente;
    }

}
