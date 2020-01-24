/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao.imp;

import aplicacion.datos.hibernate.configuracion.HibernateUtil;
import aplicacion.datos.hibernate.dao.ICarreraDAO;
import aplicacion.modelo.dominio.Carrera;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author GASTON TOCONAS
 */
public class CarreraDAOImp implements ICarreraDAO, Serializable {
    
    @Override
    public void agregarCarrera(Carrera carrera) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(carrera);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Carrera validarCarrera(Carrera carrera) {
        Carrera carreraAux = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Carrera.class);
        criteria.add(Restrictions.like("nombre", carrera.getNombre()));
        if (!criteria.list().isEmpty()) {
            carreraAux = (Carrera) criteria.list().get(0);
        }
        session.close();
        return carreraAux;
    }

    @Override
    public List<Carrera> obtenerCarreras() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Carrera.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.addOrder(Order.asc("nombre"));
        List<Carrera> carreras = null;
        if (!criteria.list().isEmpty()) {
            carreras = criteria.list();
        }
        session.close();
        return carreras;
    }

    @Override
    public void modificarCarrera(Carrera carrera) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(carrera);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void eliminarCarrera(Carrera carrera) {
        carrera.setEstado(false);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(carrera);
        session.getTransaction().commit();
        session.close();
    }
}
