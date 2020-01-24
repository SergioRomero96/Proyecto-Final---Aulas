/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion.datos.hibernate.dao.imp;

import aplicacion.datos.hibernate.configuracion.HibernateUtil;
import aplicacion.datos.hibernate.dao.InscripcionAlumnoDAO;
import aplicacion.modelo.dominio.InscripcionAlumno;
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
public class InscripcionAlumnoDAOImp implements InscripcionAlumnoDAO, Serializable{

    @Override
    public InscripcionAlumno validarIscripcion(InscripcionAlumno inscripcionAlumno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(InscripcionAlumno.class);
        criteria.createAlias("docenteMateria", "dm");
        criteria.add(Restrictions.like("dm.codigo", inscripcionAlumno.getDocenteMateria().getCodigo()));
        criteria.createAlias("alumno", "a");
        criteria.add(Restrictions.like("a.codigo", inscripcionAlumno.getAlumno().getCodigo()));
        criteria.add(Restrictions.eq("estado", true));
        InscripcionAlumno inscripcionAlumnoAux = null;
        if(!criteria.list().isEmpty()){
            inscripcionAlumnoAux = (InscripcionAlumno) criteria.list().get(0);
        }
        session.close();
        return inscripcionAlumnoAux;
    }

    @Override
    public void agregar(InscripcionAlumno inscripcionAlumno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(inscripcionAlumno);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void borrar(InscripcionAlumno inscripcionAlumno) {
        inscripcionAlumno.setEstado(false);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(inscripcionAlumno);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<InscripcionAlumno> obtenerInscripcionAlumnos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(InscripcionAlumno.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.createAlias("docenteMateria", "dm");
        criteria.addOrder(Order.asc("dm.anio"));
        List<InscripcionAlumno> inscripcionAlumnos = new ArrayList<>();
        if (!criteria.list().isEmpty()){
            inscripcionAlumnos = criteria.list();
        }
        session.close();
        return inscripcionAlumnos;
    }

}
