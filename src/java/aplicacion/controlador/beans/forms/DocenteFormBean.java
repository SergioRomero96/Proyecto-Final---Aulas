/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans.forms;

import aplicacion.controlador.beans.DocenteBean;
import aplicacion.controlador.beans.DocenteMateriaBean;
import aplicacion.controlador.beans.PerfilBean;
import aplicacion.controlador.beans.UsuarioBean;
import aplicacion.datos.hibernate.dao.IDocenteDAO;
import aplicacion.datos.hibernate.dao.IPerfilDAO;
import aplicacion.datos.hibernate.dao.imp.DocenteDAOImp;
import aplicacion.datos.hibernate.dao.imp.PerfilDAOImp;
import aplicacion.modelo.dominio.Docente;
import aplicacion.modelo.dominio.DocenteMateria;
import aplicacion.modelo.dominio.Perfil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
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
public class DocenteFormBean implements Serializable {

    @ManagedProperty(value = "#{docenteBean}")
    private DocenteBean docenteBean;

    @ManagedProperty(value = "#{perfilBean}")
    private PerfilBean perfilBean;

    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean;

    /**
     * Creates a new instance of DocenteFormBean
     */
    public DocenteFormBean() {
    }

    /**
     *  Método que permite exportar el archivo pdf, una lista de docente
     * @param actionEvent
     * @throws JRException
     * @throws IOException
     */
    public void exportarDocentesPdf(ActionEvent actionEvent) throws JRException, IOException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportDocentes.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, new JRBeanCollectionDataSource(obtenerDocentes()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.addHeader("Content-disposition", "attachment; filename=reportDocentes.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * Metodo que permite obtener el docente que esta en sesion
     *
     * @return el docente en sesion
     */
    public Docente getDocenteSesion() {
        Perfil perfil = (Perfil) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("perfilValidado");
        IDocenteDAO docenteDAO = new DocenteDAOImp();
        return docenteDAO.obtenerDocentePorPerfil(perfil);
    }

    /**
     * Metodo que permite obtener las materias que se le asigno al docente
     *
     * @return la lista de materias que dicta el docente
     */
    public List<DocenteMateria> obtenerMateriaDocente() {
        List<DocenteMateria> inscripcionAlumnos = new ArrayList<>();
        DocenteMateriaBean dmb = new DocenteMateriaBean();
        for (DocenteMateria dm : dmb.obtenerMateriasAsignadas()) {
            if (dm.getDocente().getCodigo() == getDocenteSesion().getCodigo()) {
                inscripcionAlumnos.add(dm);
            }
        }
        return inscripcionAlumnos;
    }

    /**
     * Metodo que permite actualizar el perfil del docente
     */
    public void actualizarPerfil() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        docenteBean.modificarPerfil();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.perfilActualizado"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    /**
     * Metodo que verifica si el legajo ya fue asignado
     *
     * @return false si el legajo no existe
     */
    public boolean validarLegajo() {
        return docenteBean.validarLegajo() == null;
    }

    /**
     * Metodo que verifica si el usuario ya fue creado
     *
     * @return false si el usuario no existe
     */
    public boolean validarUsuario() {
        IPerfilDAO perfilDAO = new PerfilDAOImp();
        return perfilDAO.obtenerPerfil(perfilBean.getPerfil().getUsuario()) == null;
    }

    /**
     * Metodo que permite guardar un docente en la tabla docentes, antes de
     * guardarlo, verifica si el legajo y el usuario ya fueron asignados
     */
    public void guardarDocente() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        perfilBean.getPerfil().getUsuario().setTipoUsuario("Administrativo");
        perfilBean.getPerfil().getUsuario().setEstado(true);
        docenteBean.getDocente().setEstado(true);
        docenteBean.getDocente().setPerfil(perfilBean.getPerfil());
        docenteBean.getDocente().getPerfil().setUsuario(perfilBean.getPerfil().getUsuario());
        if (validarLegajo() == true) {
            if (validarUsuario() == true) {
                docenteBean.guardarDocente();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.docenteAgreCorrect"), null);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                docenteBean.setDocente(new Docente());
                perfilBean.setPerfil(new Perfil());
            } else {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("men.docenteAgreInvalid"), null);
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            }
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("men.docenteAgLegajoInv"), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    /**
     * Metodo que permite actualizar los datos del docente
     */
    public void actualizarDocente() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        docenteBean.modificarDocente();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.docenteActualizado"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('dialogEditarDocente').hide();");
    }

    /**
     * Metodo que permite Eliminar(borrado logico) un docente
     */
    public void eliminarDocente() {
        FacesContext jsfCtx= FacesContext.getCurrentInstance();
        ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msg");
        docenteBean.getDocente().getPerfil().getUsuario().setEstado(false);
        docenteBean.eliminarDocente();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("men.docenteEliminado"), null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        RequestContext.getCurrentInstance().execute("PF('dialogEliminarDocente').hide();");
    }

    /**
     * Metodo que permite obtener todos los docentes de la tabla docentes
     *
     * @return la lista de docentes
     */
    public List<Docente> obtenerDocentes() {
        return docenteBean.obtenerDocentes();
    }

    /**
     * @return the docenteBean
     */
    public DocenteBean getDocenteBean() {
        return docenteBean;
    }

    /**
     * @param docenteBean the docenteBean to set
     */
    public void setDocenteBean(DocenteBean docenteBean) {
        this.docenteBean = docenteBean;
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

    /**
     * @return the usuarioBean
     */
    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    /**
     * @param usuarioBean the usuarioBean to set
     */
    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

}
