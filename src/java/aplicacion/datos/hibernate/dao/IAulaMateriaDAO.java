/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao;

import aplicacion.modelo.dominio.AulaMateria;
import aplicacion.modelo.dominio.DocenteMateria;
import java.util.List;

/**
 *
 * @author GASTON TOCONAS
 */
public interface IAulaMateriaDAO {
    public void agregar(AulaMateria aulaMateria);
    public void modificar(AulaMateria aulaMateria);
    public void eliminar(AulaMateria aulaMateria);
    public AulaMateria validarAulaMateria(AulaMateria aulaMateria);
    public List<AulaMateria> obtenerAulaMateria();
    public List<AulaMateria> obtenerAulaMateriaDM(DocenteMateria docenteMateria);
    public AulaMateria obtenerAulaMateria(DocenteMateria docenteMateria);
}
