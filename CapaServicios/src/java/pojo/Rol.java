package pojo;

public class Rol {

    private Integer idRole;
    private String tipoRol;

    public Rol() {

    }

    public Rol(Integer idRole, String tipoRol) {
        this.idRole = idRole;
        this.tipoRol = tipoRol;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    @Override
    public String toString() {
        return "Rol{" + "idRole=" + idRole + ", tipoRol=" + tipoRol + '}';
    }

}
