/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao.imp;

import aplicacion.datos.hibernate.configuracion.HibernateUtil;
import aplicacion.datos.hibernate.dao.IDocenteMateriaDAO;
import aplicacion.modelo.dominio.Docente;
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
public class DocenteMateriaDAOImp implements IDocenteMateriaDAO, Serializable {

    @Override
    public void agregar(DocenteMateria docenteMateria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(docenteMateria);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void modificar(DocenteMateria docenteMateria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(docenteMateria);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void eliminar(DocenteMateria docenteMateria) {
        docenteMateria.setEstado(false);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(docenteMateria);
        session.getTransaction().commit();
        session.close();
    }
    
    /**
     *
     * @param docenteMateria
     * @return
     */
    @Override
    public DocenteMateria validarAsignacionMateria(DocenteMateria docenteMateria){
        DocenteMateria docenteMateriaAux = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(DocenteMateria.class);
        criteria.createAlias("materia", "m");
        criteria.add(Restrictions.like("m.codigo", docenteMateria.getMateria().getCodigo()));
        criteria.createAlias("docente", "d");
        criteria.add(Restrictions.like("d.codigo", docenteMateria.getDocente().getCodigo()));
        criteria.add(Restrictions.eq("estado", true));
        if (!criteria.list().isEmpty()) {
            docenteMateriaAux = (DocenteMateria) criteria.list().get(0);
        }
        session.close();
        return docenteMateriaAux;
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<DocenteMateria> obtenerMateriasAsignadas(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(DocenteMateria.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.createAlias("docente", "d");
        criteria.addOrder(Order.asc("d.codigo"));
        List<DocenteMateria> docenteMaterias = new ArrayList();
        if (!criteria.list().isEmpty()) {
            docenteMaterias = criteria.list();
        }
        session.close();
        return docenteMaterias;
    }

    @Override
    public DocenteMateria obtenerDocenteMateria(Docente docente) {
        DocenteMateria docenteAux = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(DocenteMateria.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.createAlias("docente", "d");
        criteria.add(Restrictions.like("d.codigo", docente.getCodigo()));
        
        if (!criteria.list().isEmpty()) {
            docenteAux = (DocenteMateria) criteria.list().get(0);
        }
        
        session.close();
        return docenteAux;
    }
    
}
