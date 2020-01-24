/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao.imp;

import aplicacion.datos.hibernate.configuracion.HibernateUtil;
import aplicacion.datos.hibernate.dao.IAulaMateriaDAO;
import aplicacion.modelo.dominio.AulaMateria;
import aplicacion.modelo.dominio.DocenteMateria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author GASTON TOCONAS
 */
public class AulaMateriaDAOImp implements IAulaMateriaDAO, Serializable {

    @Override
    public void agregar(AulaMateria aulaMateria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(aulaMateria);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void modificar(AulaMateria aulaMateria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(aulaMateria);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void eliminar(AulaMateria aulaMateria) {
        aulaMateria.setEstado(false);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(aulaMateria);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public AulaMateria validarAulaMateria(AulaMateria aulaMateria) {
        AulaMateria aulaMateriaAux = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AulaMateria.class);
        criteria.createAlias("aula", "a");
        criteria.add(Restrictions.like("a.codigo", aulaMateria.getAula().getCodigo()));
        criteria.createAlias("docenteMateria","dc");
        criteria.add(Restrictions.like("dc.codigo", aulaMateria.getDocenteMateria().getCodigo()));
        criteria.add(Restrictions.eq("estado", true));
        if (!criteria.list().isEmpty()) {
            aulaMateriaAux = (AulaMateria) criteria.list().get(0);
        }
        session.close();
        return aulaMateriaAux;
    }

    @Override
    public List<AulaMateria> obtenerAulaMateria() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AulaMateria.class);
        criteria.add(Restrictions.eq("estado", true));
//        criteria.createAlias("carrera", "c");
        criteria.addOrder(Order.asc("dia"));
        criteria.addOrder(Order.asc("franjaHoraria"));
        List<AulaMateria> aulaMaterias = new ArrayList<>();
        if (!criteria.list().isEmpty()) {
            aulaMaterias = criteria.list();
        }
        session.close();
        return aulaMaterias;
    }
    
    
    @Override
    public List<AulaMateria> obtenerAulaMateriaDM(DocenteMateria docenteMateria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AulaMateria.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.createAlias("docenteMateria", "dm");
        criteria.add(Restrictions.like("dm.codigo", docenteMateria.getCodigo()));
        criteria.add(Restrictions.eq("estado", true));
//        criteria.createAlias("carrera", "c");
        criteria.addOrder(Order.asc("dia"));
        criteria.addOrder(Order.asc("franjaHoraria"));
        List<AulaMateria> aulaMaterias = new ArrayList<>();
        if (!criteria.list().isEmpty()) {
            aulaMaterias = criteria.list();
        }
        session.close();
        return aulaMaterias;
    }

    @Override
    public AulaMateria obtenerAulaMateria(DocenteMateria docenteMateria) {
        AulaMateria materiaAux = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AulaMateria.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.createAlias("docenteMateria", "dm");
        criteria.add(Restrictions.like("dm.codigo", docenteMateria.getCodigo()));
        
        if (!criteria.list().isEmpty()) {
            materiaAux = (AulaMateria) criteria.list().get(0);
        }
        
        session.close();
        return materiaAux;
    }
}
