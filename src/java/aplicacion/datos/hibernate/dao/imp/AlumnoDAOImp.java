/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion.datos.hibernate.dao.imp;

import aplicacion.datos.hibernate.configuracion.HibernateUtil;
import aplicacion.datos.hibernate.dao.IAlumnoDAO;
import aplicacion.modelo.dominio.Alumno;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * @author Sergio Romero
 */
public class AlumnoDAOImp implements IAlumnoDAO, Serializable{

    
    @Override
    public Alumno obtenerAlumnoPorPerfil(Perfil perfil) {
        Alumno alumno = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Alumno.class);
        criteria.createAlias("perfil", "per");
        criteria.add(Restrictions.like("per.dni", perfil.getDni()));
        if (!criteria.list().isEmpty()) {
            alumno = (Alumno) criteria.list().get(0);
        }
        session.close();
        return alumno;
    }
    
    @Override
    public Alumno obtenerAlumnoPorUsuario(Usuario usuario) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Alumno.class);
        criteria.createAlias("perfil", "per");
        criteria.add(Restrictions.like("per.usuario.nombreUsuario", usuario.getNombreUsuario()));
        Alumno alumno = null;
        if(!criteria.list().isEmpty()){
            alumno = (Alumno) criteria.list().get(0);
        }
        session.close();
        return alumno;
    }
    

    @Override
    public void modificar(Alumno alumno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(alumno);
        session.getTransaction().commit();
        session.close();
        
    }

    
    @Override
    public void eliminarUsuarioAlumno(Alumno alumno){
        alumno.getPerfil().getUsuario().setEstado(false);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(alumno);
        session.getTransaction().commit();
        session.close();
    }
    
    @Override
    public void agregar(Alumno alumno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(alumno);
        session.getTransaction().commit();
        session.close();
    }



    @Override
    public List<Alumno> obtenerAlumnos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Alumno.class);
        criteria.createAlias("perfil", "p");
        criteria.addOrder(Order.asc("p.apellido"));
        criteria.addOrder(Order.asc("p.nombre"));
        List<Alumno> alumnos = null;
        if (!criteria.list().isEmpty()) {
            alumnos = criteria.list();
        }
        session.close();
        return alumnos;
    }

    
    @Override
    public Alumno validarLibretaUniversitaria(Alumno alumno){
        Alumno alumnoAux = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Alumno.class);
        criteria.add(Restrictions.like("libretaUniversitaria", alumno.getLibretaUniversitaria()));
        if (!criteria.list().isEmpty()) {
            alumnoAux = (Alumno) criteria.list().get(0);
        }
        session.close();
        return alumnoAux;
    }
    
    

}
