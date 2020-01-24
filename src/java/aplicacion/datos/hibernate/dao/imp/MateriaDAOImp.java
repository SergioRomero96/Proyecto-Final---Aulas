/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao.imp;

import aplicacion.datos.hibernate.configuracion.HibernateUtil;
import aplicacion.datos.hibernate.dao.IMateriaDAO;
import aplicacion.modelo.dominio.Carrera;
import aplicacion.modelo.dominio.Materia;
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
public class MateriaDAOImp implements IMateriaDAO, Serializable {

    @Override
    public void agregar(Materia materia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(materia);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void modificar(Materia materia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(materia);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void eliminar(Materia materia) {
        materia.setEstado(false);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(materia);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Materia validarMateria(Materia materia) {
        Carrera carrera = new Carrera();
        Materia materiaAux = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Materia.class);
        criteria.add(Restrictions.like("nombre", materia.getNombre()));
        criteria.createAlias("carrera", "c");
        criteria.add(Restrictions.like("c.nombre", carrera.getNombre()));
        if (!criteria.list().isEmpty()) {
            materiaAux = (Materia) criteria.list().get(0);
        }
        session.close();
        return materiaAux;
    }

    @Override
    public List<Materia> obtenerMaterias() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Materia.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.createAlias("carrera", "c");
        criteria.addOrder(Order.asc("c.nombre"));
        List<Materia> materias = null;
        if (!criteria.list().isEmpty()) {
            materias = criteria.list();
        }
        session.close();
        return materias;
    }
    
    /**
     *
     * @param carrera
     * @return
     */
    @Override
    public List<Materia> obtenerMateriasPorCarrera(Carrera carrera) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Materia.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.createAlias("carrera", "c");
        criteria.add(Restrictions.like("c.codigo", carrera.getCodigo()));
        List<Materia> materias = new ArrayList<>();
        if (!criteria.list().isEmpty()) {
            materias = criteria.list();
        }
        session.close();
        return materias;
    }
}
