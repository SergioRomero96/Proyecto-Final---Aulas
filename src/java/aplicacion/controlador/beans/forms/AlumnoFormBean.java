/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.AlumnoBean;
import aplicacion.controlador.beans.CarreraBean;
import aplicacion.controlador.beans.InscripcionAlumnoBean;
import aplicacion.controlador.beans.PerfilBean;
import aplicacion.datos.hibernate.dao.IAlumnoDAO;
import aplicacion.datos.hibernate.dao.IPerfilDAO;
import aplicacion.datos.hibernate.dao.IUsuarioDAO;
import aplicacion.datos.hibernate.dao.imp.AlumnoDAOImp;
import aplicacion.datos.hibernate.dao.imp.PerfilDAOImp;
import aplicacion.datos.hibernate.dao.imp.UsuarioDAOImp;
import aplicacion.modelo.dominio.Alumno;
import aplicacion.modelo.dominio.Carrera;
import aplicacion.modelo.dominio.InscripcionAlumno;
import aplicacion.modelo.dominio.Perfil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Sergio Romero
 */
@ManagedBean
@ViewScoped
public class AlumnoFormBean implements Serializable {

    @ManagedProperty(value = "#{alumnoBean}")
    private AlumnoBean alumnoBean;

    @ManagedProperty(value = "#{perfilBean}")
    private PerfilBean perfilBean;

    @ManagedProperty(value = "#{carreraBean}")
    private CarreraBean carreraBean;

    /**
     * Creates a new instance of AlumnoFormBean
     */
    public AlumnoFormBean() {
    }

    /**
     *  Método que permite exportar el archivo pdf, una lista de alumnos
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     */
    public void exportarAlumnosPdf(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportAlumnos.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(obtenerAlumnos()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=reportAlumnos.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }
    /**
     * Metodo que permite obtener el alumno en sesion por medio del perfil
     * @return el alumno que esta en sesion
     */
    public Alumno getAlumnoSesion() {
        Perfil perfil = (Perfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfilValidado");
        IAlumnoDAO alumnoDAO = new AlumnoDAOImp();
        return alumnoDAO.obtenerAlumnoPorPerfil(perfil);
    }

    /**
     * Metodo que permite actualizar el perfil de alumno
     */
    public void actualizarPerfil() {
        alumnoBean.modificarPerfil();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil actualizado", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    /**
     * Metodo que permite verificar si el usuario que se esta creando existe en
     * la tabla de usuario
     * @return false si el usuario de ese alumno no existe
     */
    public boolean verificarUsuario() {
        IPerfilDAO perfilDAO = new PerfilDAOImp();
        return perfilDAO.obtenerPerfil(alumnoBean.getAlumno().getPerfil().getUsuario()) == null;
    }

    /**
     * Metodo que permite eliminar la cuenta de usuario del alumno
     */
    public void eliminarCuentaUsuario() {
        alumnoBean.eliminarCuentaUsuario();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario del Alumno Eliminado", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarAlumno').hide();");
    }

    /**
     * Metodo que permite actualizar los datos academicos del alumno
     */
    public void actualizarDatosAlumno() {
        alumnoBean.modificarAlumno();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos del Alumno Actualizado", null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        alumnoBean.setAlumno(new Alumno());
        RequestContext.getCurrentInstance().execute("PF('dialogEditarAlumno').hide();");
    }

    /**
     * Metodo que permite verificar si el usuario existe
     * @return false si el usuario no existe
     */
    public boolean validarUsuario() {
        IPerfilDAO perfilDAO = new PerfilDAOImp();
        return perfilDAO.obtenerPerfil(perfilBean.getPerfil().getUsuario()) == null;
    }

    /**
     * Metodo que permite agregar un alumno a la tabla de alumno, antes de
     * crearlo verifica que no exista el usuario que esta creando
     */
    public void registrarAlumno() {
        perfilBean.getPerfil().getUsuario().setTipoUsuario("Final");
        perfilBean.getPerfil().getUsuario().setEstado(true);
        alumnoBean.getAlumno().setPerfil(perfilBean.getPerfil());
        alumnoBean.getAlumno().getPerfil().setUsuario(perfilBean.getPerfil().getUsuario());

        alumnoBean.getAlumno().setCarrera(carreraBean.getCarrera());

        if (validarUsuario() == true) {

            alumnoBean.guardarAlumno();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alumno Agregado Correctamente", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            alumnoBean.setAlumno(new Alumno());
            perfilBean.setPerfil(new Perfil());

        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario ya existe", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    /**
     * Metodo que permite obtener todas las carreras que estan cargadas en
     * nuestra tabla de carreras
     * @return la lista de carreras
     */
    public List<Carrera> obtenerCarreras() {
        return carreraBean.obtenerCarreras();
    }

    /**
     * Metodo que permite obtener todas los alumnos que estan cargados en la
     * tabla alumnos
     * @return la lista de alumnos
     */
    public List<Alumno> obtenerAlumnos() {
        return alumnoBean.obtenerAlumnos();
    }

    /**
     * Metodo que permite obtener todas las materias que cursa el alumno que
     * esta en sesion
     * @return la lista de Materias que cursa el alumno
     */
    public List<InscripcionAlumno> obtenerMateriasAlumno() {
        List<InscripcionAlumno> inscripcionAlumnos = new ArrayList<>();
        InscripcionAlumnoBean iab = new InscripcionAlumnoBean();
        for (InscripcionAlumno ia : iab.obtenerInscripcionAlumnos()) {
            if (ia.getAlumno().getCodigo() == getAlumnoSesion().getCodigo()) {
                inscripcionAlumnos.add(ia);
            }
        }
        return inscripcionAlumnos;
    }

    /**
     * metodo que verifica si la libreta universitaria ya fue registrada
     * @return false si no existe el usuario
     */
    public boolean validarLibretaUniversitaria() {
        return alumnoBean.validarLibretaUniversitaria() == null;
    }

    /**
     * @return the alumnoBean
     */
    public AlumnoBean getAlumnoBean() {
        return alumnoBean;
    }

    /**
     * @param alumnoBean the alumnoBean to set
     */
    public void setAlumnoBean(AlumnoBean alumnoBean) {
        this.alumnoBean = alumnoBean;
    }

    /**
     * @return the carreraBean
     */
    public CarreraBean getCarreraBean() {
        return carreraBean;
    }

    /**
     * @param carreraBean the carreraBean to set
     */
    public void setCarreraBean(CarreraBean carreraBean) {
        this.carreraBean = carreraBean;
    }

    /**
     * @return the perfilBean
     */
    public PerfilBean getPerfilBean() {
        return perfilBean;
    }

    /**
     * @param perfilBean the perfilBean to set
     */
    public void setPerfilBean(PerfilBean perfilBean) {
        this.perfilBean = perfilBean;
    }

}
