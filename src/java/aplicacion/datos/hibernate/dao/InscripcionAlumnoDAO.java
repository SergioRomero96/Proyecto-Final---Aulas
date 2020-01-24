/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao;

import aplicacion.modelo.dominio.InscripcionAlumno;
import java.util.List;

/**
 *
 * @author Sergio Romero
 */
public interface InscripcionAlumnoDAO {
    public InscripcionAlumno validarIscripcion(InscripcionAlumno inscripcionAlumno);
    public void agregar(InscripcionAlumno inscripcionAlumno);
    public void borrar(InscripcionAlumno inscripcionAlumno);
    public List<InscripcionAlumno> obtenerInscripcionAlumnos();
}
