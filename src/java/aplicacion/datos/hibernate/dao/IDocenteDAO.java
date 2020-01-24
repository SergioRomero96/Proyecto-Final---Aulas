/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao;

import aplicacion.modelo.dominio.Docente;
import aplicacion.modelo.dominio.Perfil;
import aplicacion.modelo.dominio.Usuario;
import java.util.List;

/**
 *
 * @author Sergio Romero
 */
public interface IDocenteDAO {
    public Docente obtenerDocentePorPerfil(Perfil perfil);
    public Docente validarLegajoDocente(Docente docente);
    public Docente buscarDocente(Usuario usuario);
    public void agregar(Docente docente);
    public void modificar(Docente docente);
    public void eliminar(Docente docente);
    public List<Docente> obtenerDocentes();
}
