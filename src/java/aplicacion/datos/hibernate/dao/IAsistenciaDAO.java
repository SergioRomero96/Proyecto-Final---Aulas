/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao;

import aplicacion.modelo.dominio.Asistencia;
import aplicacion.modelo.dominio.DocenteMateria;
import java.util.List;

/**
 *
 * @author GASTON TOCONAS
 */
public interface IAsistenciaDAO {
    public void agregar(Asistencia asistencia);
    public void modificar(Asistencia asistencia);
    //public void eliminar(Asistencia asistencia);
    public List<Asistencia> obtenerAsistencia();
    public Asistencia validarAsistencia(Asistencia asistencia);
    public List<Asistencia> obtenerAsistenciaPorMateria(DocenteMateria docenteMateria);
}
