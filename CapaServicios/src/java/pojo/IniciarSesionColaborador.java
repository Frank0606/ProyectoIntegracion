package pojo;

public class IniciarSesionColaborador {
    
    private Boolean error;
    private String mensaje;
    private Colaborador colaborador;

    public IniciarSesionColaborador() {
    }

    public IniciarSesionColaborador(Boolean error, String mensaje, Colaborador colaborador) {
        this.error = error;
        this.mensaje = mensaje;
        this.colaborador = colaborador;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    @Override
    public String toString() {
        return "IniciarSesionColaborador{" + "error=" + error + ", mensaje=" + mensaje + ", colaborador=" + colaborador + '}';
    }
}
