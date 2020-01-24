/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.datos.hibernate.dao;

import aplicacion.modelo.dominio.Aula;
import java.util.List;

/**
 *
 * @author GASTON TOCONAS
 */
public interface IAulaDAO {
    public Aula validarNombreAula (Aula aula);
    public void agregarAula(Aula aula);
    public void modificarAula(Aula aula);
    public void eliminarAula(Aula aula);
    public List<Aula> obtenerAulas();
}
