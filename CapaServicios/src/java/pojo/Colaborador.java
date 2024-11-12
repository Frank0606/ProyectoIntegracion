package pojo;

public class Colaborador {
    
    private Integer idColaborador;
    private String nombreColaborador;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String curp;
    private String correoElectronico;
    private String noPersonal;
    private String contrasenia;
    private byte[] fotografia;
    private Integer idRol;
    private String tipoRol;

    public Colaborador() {
    }

    public Colaborador(Integer idColaborador, String nombreColaborador, String apellidoMaterno, String apellidoPaterno, String curp, String correoElectronico, String noPersonal, String contrasenia, byte[] fotografia, Integer idRol, String tipoRol) {
        this.idColaborador = idColaborador;
        this.nombreColaborador = nombreColaborador;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.curp = curp;
        this.correoElectronico = correoElectronico;
        this.noPersonal = noPersonal;
        this.contrasenia = contrasenia;
        this.fotografia = fotografia;
        this.idRol = idRol;
        this.tipoRol = tipoRol;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getNombreColaborador() {
        return nombreColaborador;
    }

    public void setNombreColaborador(String nombreColaborador) {
        this.nombreColaborador = nombreColaborador;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNoPersonal() {
        return noPersonal;
    }

    public void setNoPersonal(String noPersonal) {
        this.noPersonal = noPersonal;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
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
        return "Colaborador{" + "idColaborador=" + idColaborador + ", nombreColaborador=" + nombreColaborador + ", apellidoMaterno=" + apellidoMaterno + ", apellidoPaterno=" + apellidoPaterno + ", curp=" + curp + ", correoElectronico=" + correoElectronico + ", noPersonal=" + noPersonal + ", contrasenia=" + contrasenia + ", fotografia=" + fotografia + ", idRol=" + idRol + ", tipoRol=" + tipoRol + '}';
    }
}
