package pojo;

public class IniciarSesionCliente {
    
    private Boolean error;
    private String mensaje;
    private Cliente cliente;

    public IniciarSesionCliente() {
    }

    public IniciarSesionCliente(Boolean error, String mensaje, Cliente cliente) {
        this.error = error;
        this.mensaje = mensaje;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "IniciarSesionCliente{" + "error=" + error + ", mensaje=" + mensaje + ", cliente=" + cliente + '}';
    }
}
