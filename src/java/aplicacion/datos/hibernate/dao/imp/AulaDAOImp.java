/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao.imp;

import aplicacion.datos.hibernate.configuracion.HibernateUtil;
import aplicacion.datos.hibernate.dao.IAulaDAO;
import aplicacion.modelo.dominio.Aula;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author GASTON TOCONAS
 */
public class AulaDAOImp implements IAulaDAO, Serializable {

    @Override
    public void agregarAula(Aula aula) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(aula);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Aula validarNombreAula(Aula aula) {
        Aula aulaAux = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Aula.class);
        criteria.add(Restrictions.like("nombre", aula.getNombre()));
        if (!criteria.list().isEmpty()) {
            aulaAux = (Aula) criteria.list().get(0);
        }
        session.close();
        return aulaAux;
    }

    @Override
    public void modificarAula(Aula aula) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(aula);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Aula> obtenerAulas() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Aula.class);
        criteria.add(Restrictions.eq("estado", true));
        List<Aula> aulas = new ArrayList<>();
        if (!criteria.list().isEmpty()) {
            aulas = criteria.list();
        }
        session.close();
        return aulas;
    }

    @Override
    public void eliminarAula(Aula aula) {
        aula.setEstado(false);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(aula);
        session.getTransaction().commit();
        session.close();
    }
}
