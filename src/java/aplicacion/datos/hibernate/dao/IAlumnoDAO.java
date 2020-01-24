/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao;

import aplicacion.modelo.dominio.Alumno;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Usuario;
import java.util.List;

/**
 *
 * @author Sergio Romero
 */
public interface IAlumnoDAO {
    public Alumno obtenerAlumnoPorPerfil(Perfil perfil);
    public Alumno obtenerAlumnoPorUsuario(Usuario usuario);
    public void agregar(Alumno alumno);
    public void modificar(Alumno alumno);
    public List<Alumno> obtenerAlumnos();
    public Alumno validarLibretaUniversitaria(Alumno alumno);
    public void eliminarUsuarioAlumno(Alumno alumno);
}
