/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.controlador.beans;

import java.io.Serializable;
import java.util.TreeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Sergio Romero
 */
@ManagedBean
@SessionScoped
public class SwitcherBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private TreeMap<String, String> themes;
    private GuestPreferences gp = new GuestPreferences();
    private String theme;
    /**
     * Creates a new instance of SwitcherBean
     */
    public SwitcherBean() {
        theme = gp.getTheme();
        
        themes = new TreeMap<String, String>();
        themes.put("Afterdark", "afterdark");
        themes.put("Afternoon", "afternoon");
        themes.put("Afterwork", "afterwork");
        themes.put("Aristo", "aristo");
        themes.put("Black-Tie", "black-tie");
        themes.put("Blitzer", "blitzer");
        themes.put("Bluesky", "bluesky");
        themes.put("Bootstrap", "bootstrap");
        themes.put("Casablanca", "casablanca");
        themes.put("Cupertino", "cupertino");
        themes.put("Cruze", "cruze");
        themes.put("Dark-Hive", "dark-hive");
        themes.put("Delta", "delta");
        themes.put("Dot-Luv", "dot-luv");
        themes.put("Eggplant", "eggplant");
        themes.put("Excite-Bike", "excite-bike");
        themes.put("Flick", "flick");
        themes.put("Glass-X", "glass-x");
        themes.put("Home", "home");
        themes.put("Hot-Sneaks", "hot-sneaks");
        themes.put("Humanity", "humanity");
        themes.put("Le-Frog", "le-frog");
        //themes.put("Midnight", "midnight");
        themes.put("Mint-Choc", "mint-choc");
        themes.put("Omega", "omega");
        themes.put("Overcast", "overcast");
        themes.put("Pepper-Grinder", "pepper-grinder");
        themes.put("Redmond", "redmond");
        themes.put("Rocket", "rocket");
        themes.put("Sam", "sam");
        themes.put("Smoothness", "smoothness");
        themes.put("South-Street", "south-street");
        themes.put("Start", "start");
        themes.put("Sunny", "sunny");
        //themes.put("Swanky-Purse", "swanky-purse");
        themes.put("Trontastic", "trontastic");
        themes.put("UI-Darkness", "ui-darkness");
        themes.put("UI-Lightness", "ui-lightness");
        themes.put("Vader", "vader");
        
        
    }

    /**
     * @return the themes
     */
    public TreeMap<String, String> getThemes() {
        return themes;
    }

    /**
     * @param themes the themes to set
     */
    public void setThemes(TreeMap<String, String> themes) {
        this.themes = themes;
    }

    /**
     * @return the gp
     */
    public GuestPreferences getGp() {
        return gp;
    }

    /**
     * @param gp the gp to set
     */
    public void setGp(GuestPreferences gp) {
        this.gp = gp;
    }

    /**
     * @return the theme
     */
    public String getTheme() {
        return theme;
    }

    /**
     * @param theme the theme to set
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    public void saveTheme(){
        gp.setTheme(theme);
    }
    
}
