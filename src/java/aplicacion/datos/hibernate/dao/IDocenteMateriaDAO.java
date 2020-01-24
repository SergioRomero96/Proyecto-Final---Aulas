/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao;

import aplicacion.modelo.dominio.Docente;
import aplicacion.modelo.dominio.DocenteMateria;
import java.util.List;

/**
 *
 * @author GASTON TOCONAS
 */
public interface IDocenteMateriaDAO {
    public void agregar(DocenteMateria docenteMateria);
    public void modificar(DocenteMateria docenteMateria);
    public void eliminar(DocenteMateria docenteMateria);
    public DocenteMateria validarAsignacionMateria(DocenteMateria docenteMateria);
    public List<DocenteMateria> obtenerMateriasAsignadas();
    public DocenteMateria obtenerDocenteMateria(Docente docente);
}
