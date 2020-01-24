/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao;

import aplicacion.modelo.dominio.Carrera;
import java.util.List;

/**
 *
 * @author GASTON TOCONAS
 */
public interface ICarreraDAO {
    public Carrera validarCarrera(Carrera carrera);
    public void agregarCarrera(Carrera carrera);
    public void modificarCarrera(Carrera carrera);
    public void eliminarCarrera(Carrera carrera);
    public List<Carrera> obtenerCarreras();
}
