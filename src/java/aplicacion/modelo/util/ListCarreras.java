/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion.modelo.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sergio Romero
 */
public class ListCarreras implements Serializable {
    private List<String> carreras;

    public ListCarreras() {
        carreras = new ArrayList<>();
        
        /* Carreras de Grado */
        carreras.add("Ingeniería Informática");
        carreras.add("Ingeniería Industrial");
        carreras.add("Ingeniería Química");
        carreras.add("Ingeniería de Minas");
        carreras.add("Licenciatura en Sistemas");
        carreras.add("Licenciatura en Tecnología de los Alimentos");
        carreras.add("Licenciatura en Ciencias Geológicas");
        
        /* Carreras de PreGrado */
        carreras.add("Analista Programador Universitario");
        carreras.add("Técnico Universitario en Procesamiento de Minerales");
        carreras.add("Técnico Universitario en Explotación de Minas");
        carreras.add("Técnico Universitario en Ciencias de la Tierra");
        carreras.add("Técnico Universitario en Perforaciones");
        carreras.add("Técnico Universitario en Ciencias de la Tierra orientada a Petróleo");        
    }

    public ListCarreras(List<String> carreras) {
        this.carreras = carreras;
    }
    
    /**
     * @return the carreras
     */
    public List<String> getCarreras() {
        return carreras;
    }

    /**
     * @param carreras the carreras to set
     */
    public void setCarreras(List<String> carreras) {
        this.carreras = carreras;
    }
    
    
    
}
