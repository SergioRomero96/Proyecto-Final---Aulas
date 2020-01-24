package aplicacion.modelo.dominio;
// Generated 07-jun-2018 9:55:55 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Docente generated by hbm2java
 */
public class Docente  implements java.io.Serializable {


    private Integer codigo;
    private Perfil perfil;
    private String legajo;
    private String cargo;
    private boolean estado;
    private Set docentesMateriases = new HashSet(0);

    public Docente() {
    }

    public Docente(Perfil perfil, String legajo, String cargo, boolean estado) {
        this.perfil = perfil;
        this.legajo = legajo;
        this.cargo = cargo;
        this.estado = estado;
    }
    
    

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the perfil
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the legajo
     */
    public String getLegajo() {
        return legajo;
    }

    /**
     * @param legajo the legajo to set
     */
    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the estado
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * @return the docentesMateriases
     */
    public Set getDocentesMateriases() {
        return docentesMateriases;
    }

    /**
     * @param docentesMateriases the docentesMateriases to set
     */
    public void setDocentesMateriases(Set docentesMateriases) {
        this.docentesMateriases = docentesMateriases;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Docente) && (codigo != null)
            ? codigo.equals(((Docente) other).codigo)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (codigo != null)
            ? (this.getClass().hashCode() + codigo.hashCode())
            : super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Docente[%d]", codigo);
    }



}


