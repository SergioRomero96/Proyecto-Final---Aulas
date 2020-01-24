/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import java.io.Serializable;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sergio Romero
 */
@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable {

    private static final long serialVersionUID = 1L;
    private String theme = "omega"; //tema por defecto

    /**
     * Metodo que permite obtener el tema
     * @return el tema
     */
    public String getTheme() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.containsKey("theme")) {
            theme = params.get("theme");
        }
        return theme;
    }

    /**
     * Metodo que permite asignar el tema
     * @param theme the themes to set
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

}
