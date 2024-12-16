package pojo;

public class Rol {

    private Integer idRol;
    private String tipoRol;

    public Rol() {
    }

    public Rol(Integer idRol, String tipoRol) {
        this.idRol = idRol;
        this.tipoRol = tipoRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    @Override
    public String toString() {
        return "Rol{" + "idRol=" + idRol + ", tipoRol=" + tipoRol + '}';
    }
}
