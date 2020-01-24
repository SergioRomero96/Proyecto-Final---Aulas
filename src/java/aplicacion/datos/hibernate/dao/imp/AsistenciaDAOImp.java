/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao.imp;

import aplicacion.datos.hibernate.configuracion.HibernateUtil;
import aplicacion.datos.hibernate.dao.IAsistenciaDAO;
import aplicacion.modelo.dominio.Asistencia;
import aplicacion.modelo.dominio.DocenteMateria;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author GASTON TOCONAS
 */
public class AsistenciaDAOImp implements IAsistenciaDAO {

    @Override
    public void agregar(Asistencia asistencia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(asistencia);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void modificar(Asistencia asistencia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(asistencia);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Asistencia> obtenerAsistencia() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Asistencia.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.addOrder(Order.asc("fecha"));
        List<Asistencia> asistencias = new ArrayList<>();
        if (!criteria.list().isEmpty()) {
            asistencias = criteria.list();
        }
        session.close();
        return asistencias;
    }

    @Override
    public Asistencia validarAsistencia(Asistencia asistencia) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Asistencia.class);
        criteria.add(Restrictions.eq("estado", true));
        criteria.createAlias("inscripcionAlumno", "ia");
        criteria.add(Restrictions.like("ia.codigo", asistencia.getInscripcionAlumno().getCodigo()));
        criteria.add(Restrictions.eq("fecha", asistencia.getFecha()));
        Asistencia asistenciaAux = null;
        if (!criteria.list().isEmpty()) {
            asistenciaAux = (Asistencia) criteria.list().get(0);
        }
        session.close();
        return asistenciaAux;
    }

    @Override
    public List<Asistencia> obtenerAsistenciaPorMateria(DocenteMateria docenteMateria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
