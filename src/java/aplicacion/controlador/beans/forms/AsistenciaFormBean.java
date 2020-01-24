/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.AsistenciaBean;
import aplicacion.controlador.beans.AulaMateriaBean;
import aplicacion.controlador.beans.MateriaBean;
import aplicacion.datos.hibernate.dao.IAsistenciaDAO;
import aplicacion.datos.hibernate.dao.IAulaMateriaDAO;
import aplicacion.datos.hibernate.dao.IDocenteDAO;
import aplicacion.datos.hibernate.dao.IDocenteMateriaDAO;
import aplicacion.datos.hibernate.dao.InscripcionAlumnoDAO;
import aplicacion.datos.hibernate.dao.imp.AsistenciaDAOImp;
import aplicacion.datos.hibernate.dao.imp.AulaMateriaDAOImp;
import aplicacion.datos.hibernate.dao.imp.DocenteDAOImp;
import aplicacion.datos.hibernate.dao.imp.DocenteMateriaDAOImp;
import aplicacion.datos.hibernate.dao.imp.InscripcionAlumnoDAOImp;
import aplicacion.modelo.dominio.Asistencia;
import aplicacion.modelo.dominio.AulaMateria;
import aplicacion.modelo.dominio.Docente;
import aplicacion.modelo.dominio.DocenteMateria;
import aplicacion.modelo.dominio.InscripcionAlumno;
import aplicacion.modelo.dominio.Perfil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author GASTON TOCONAS
 */
@ManagedBean
@ViewScoped
public class AsistenciaFormBean implements Serializable {
    private DocenteMateria docenteMateria;
    private List<InscripcionAlumno> inscripcionAlumnos;
    private List<Asistencia> asistenciaMaterias;
    private List<AulaMateria> aulaMaterias;
    
    @ManagedProperty(value = "#{asistenciaBean}")
    private AsistenciaBean asistenciaBean;
    
    @ManagedProperty(value = "#{materiaBean}")
    private MateriaBean materiaBean;
    
    @ManagedProperty(value = "#{aulaMateriaBean}")
    private AulaMateriaBean aulaMateriaBean;
        
    /**
     * Creates a new instance of AsistenciaFormBean
     */
    public AsistenciaFormBean() {
        docenteMateria = obtenerDocenteMateria();
        System.out.println("docenteMateria: " + docenteMateria.getCodigo());
        inscripcionAlumnos = new ArrayList<>();
        asistenciaMaterias = new ArrayList<>();
        aulaMaterias = new ArrayList<>();
    }
       
    public void registrarAsistencia2() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        asistenciaBean.getAsistencia().setEstado(true);
        if (validarAsistencia() == true) {
            asistenciaBean.agregarAsistencia();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.asistenciaAgreCorrect"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            asistenciaBean.getAsistencia().setInscripcionAlumno(new InscripcionAlumno());
            asistenciaBean.getAsistencia().setAulaMateria(new AulaMateria());
            asistenciaBean.setAsistencia(new Asistencia());
            
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("men.asistenciaAgreInvalid"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
      
    
    /**
     *
     * @return
     */
    public boolean validarAsistencia() {
        return asistenciaBean.validarAsistencia() == null;
    }
    
    /**
     *
     */
    public void modificarAsistencia() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        asistenciaBean.modificarAsistencia();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.asistenciaModificada"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        asistenciaBean.setAsistencia(new Asistencia());
    }
    
    /**
     *
     * @return
     */
    public Docente getDocenteSesion() {
        Perfil perfil = (Perfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfilValidado");
        IDocenteDAO docenteDAO = new DocenteDAOImp();
        return docenteDAO.obtenerDocentePorPerfil(perfil);
    }
    
    /**
     *
     * @return
     */
    public DocenteMateria obtenerDocenteMateria() {
        IDocenteMateriaDAO docMatDAO = new DocenteMateriaDAOImp();
        return docMatDAO.obtenerDocenteMateria(getDocenteSesion());
    }
    
    /**
     *
     */
    
    public void obtenerAulaMateriaPorDocenteMateria() {
        aulaMaterias = new ArrayList<>();
        IAulaMateriaDAO aulaMateriaDAO = new AulaMateriaDAOImp();
        System.out.println("DocenteMateria Seleccionado: " + docenteMateria.getCodigo());
        for (AulaMateria am: aulaMateriaDAO.obtenerAulaMateria()) {
            System.out.println("DocenteMateria: " + am.getDocenteMateria().getCodigo());
            if (am.getDocenteMateria().getCodigo() == docenteMateria.getCodigo()) {
                System.out.println("Aula agregado");
                aulaMaterias.add(am);
            }
        }
    }
    
    public void obtenerAlumnosPorMateria() {
        inscripcionAlumnos = new ArrayList<>();
        InscripcionAlumnoDAO iadao = new InscripcionAlumnoDAOImp();
        System.out.println("DocenteMateria Seleccionado: " + docenteMateria.getCodigo());
        for (InscripcionAlumno ia:iadao.obtenerInscripcionAlumnos()) {
            System.out.println("DocenteMateria: " + ia.getDocenteMateria());
            if (ia.getDocenteMateria().getMateria().getCodigo() == docenteMateria.getMateria().getCodigo()) {
                System.out.println("Inscripcion agregado");
                inscripcionAlumnos.add(ia);
            }
        }
    }
    
    /**
     *
     * @return
     */
    public List<Asistencia> obtenerAsistencia() {
        return asistenciaBean.obtenerAsistencia();
    }
    
    public List<Date> obtenerFecha() {
        List<Date> fecha = new ArrayList<>();
        InscripcionAlumnoDAO iadao = new InscripcionAlumnoDAOImp();
        for (Asistencia asi: asistenciaBean.obtenerAsistencia()) {
            if (fecha.isEmpty()) {
                System.out.println("fecha 1 " + asi.getFecha());
                fecha.add(asi.getFecha());
            } else {
                for (Date dat: fecha) {
                    if (!dat.equals(asi.getFecha())) {
                        System.out.println("fecha adentro " + asi.getFecha());
                        fecha.add(asi.getFecha());
                    }
                }
            }
        }
        return fecha;
    }
    
    
   
    public void obtenerAsistenciaAlumnosPorDocenteMateria() {
        asistenciaMaterias = new ArrayList<>();
        IAsistenciaDAO asistenciaDAO = new AsistenciaDAOImp();
        for (Asistencia asi: asistenciaDAO.obtenerAsistencia()) {
            System.out.println("asistencia: "+ asi.getCodigo());
            if (asi.getInscripcionAlumno().getDocenteMateria().getMateria().getCodigo() == docenteMateria.getMateria().getCodigo()) {
                System.out.println("Agregado");
                asistenciaMaterias.add(asi);
            }
        }
      
    }
    
    /*public List<AulaMateria> obtenerAulaMateria() {
        return aulaMateriaBean.obtenerAulaMateria();
    }
    */ 

    /**
     *
     * @return
     */
 
    public AsistenciaBean getAsistenciaBean() {
        return asistenciaBean;
    }

    /**
     *
     * @param asistenciaBean
     */
    public void setAsistenciaBean(AsistenciaBean asistenciaBean) {
        this.asistenciaBean = asistenciaBean;
    }

    /**
     *
     * @return
     */
    public MateriaBean getMateriaBean() {
        return materiaBean;
    }

    /**
     *
     * @param materiaBean
     */
    public void setMateriaBean(MateriaBean materiaBean) {
        this.materiaBean = materiaBean;
    }

    /**
     *
     * @return
     */
    public AulaMateriaBean getAulaMateriaBean() {
        return aulaMateriaBean;
    }

    /**
     *
     * @param aulaMateriaBean
     */
    public void setAulaMateriaBean(AulaMateriaBean aulaMateriaBean) {
        this.aulaMateriaBean = aulaMateriaBean;
    }

    /**
     *
     * @return
     */
    public DocenteMateria getDocenteMateria() {
        return docenteMateria;
    }

    /**
     *
     * @param docenteMateria
     */
    public void setDocenteMateria(DocenteMateria docenteMateria) {
        this.docenteMateria = docenteMateria;
    }

    /**
     *
     * @return
     */
    public List<InscripcionAlumno> getInscripcionAlumnos() {
        return inscripcionAlumnos;
    }

    /**
     *
     * @param inscripcionAlumnos
     */
    public void setInscripcionAlumnos(List<InscripcionAlumno> inscripcionAlumnos) {
        this.inscripcionAlumnos = inscripcionAlumnos;
    }

    /**
     * @return the asistenciaMaterias
     */
    public List<Asistencia> getAsistenciaMaterias() {
        return asistenciaMaterias;
    }

    /**
     * @param asistenciaMaterias the asistenciaMaterias to set
     */
    public void setAsistenciaMaterias(List<Asistencia> asistenciaMaterias) {
        this.asistenciaMaterias = asistenciaMaterias;
    }

    /**
     * @return the aulaMaterias
     */
    public List<AulaMateria> getAulaMaterias() {
        return aulaMaterias;
    }

    /**
     * @param aulaMaterias the aulaMaterias to set
     */
    public void setAulaMaterias(List<AulaMateria> aulaMaterias) {
        this.aulaMaterias = aulaMaterias;
    }
}
