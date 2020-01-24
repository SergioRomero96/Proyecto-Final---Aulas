/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao;

import aplicacion.modelo.dominio.Carrera;
import aplicacion.modelo.dominio.Materia;
import java.util.List;

/**
 *
 * @author GASTON TOCONAS
 */
public interface IMateriaDAO {
    public Materia validarMateria(Materia materia);
    public void agregar(Materia materia);
    public void modificar(Materia materia);
    public void eliminar(Materia materia);
    public List<Materia> obtenerMaterias();
    public List<Materia> obtenerMateriasPorCarrera(Carrera carrera);
}
