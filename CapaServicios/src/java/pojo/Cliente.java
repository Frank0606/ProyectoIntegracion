package pojo;

/**
 *
 * @author Manzano
 */

public class Cliente {
    
    private Integer idCliente;
    private String telefono;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String correoElectronico;
    private String contrasenia;
    private String fotografía;
    private String nombreCliente;

    public Cliente() {
    }

    public Cliente(Integer idCliente, String telefono, String apellidoMaterno, String apellidoPaterno, String correoElectronico, String contrasenia, String fotografía, String nombreCliente) {
        this.idCliente = idCliente;
        this.telefono = telefono;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.fotografía = fotografía;
        this.nombreCliente = nombreCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getFotografía() {
        return fotografía;
    }

    public void setFotografía(String fotografía) {
        this.fotografía = fotografía;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
}
