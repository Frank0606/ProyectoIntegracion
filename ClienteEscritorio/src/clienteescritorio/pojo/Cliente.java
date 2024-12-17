/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio.pojo;

/**
 *
 * @author Manzano
 */
public class Cliente {
    
    private Integer idCliente;
    private String telefono;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String calle;
    private String colonia;
    private String cp;
    private String correoElectronico;
    private String contrasenia;
    private String fotografia;
    private String nombreCliente;
    private String numeroCasa;

    public Cliente() {
    }

    public Cliente(Integer idCliente, String telefono, String apellidoMaterno, String apellidoPaterno, String calle, String colonia, String cp, String correoElectronico, String contrasenia, String fotografia, String nombreCliente, String numeroCasa) {
        this.idCliente = idCliente;
        this.telefono = telefono;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.calle = calle;
        this.colonia = colonia;
        this.cp = cp;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.fotografia = fotografia;
        this.nombreCliente = nombreCliente;
        this.numeroCasa = numeroCasa;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
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

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}