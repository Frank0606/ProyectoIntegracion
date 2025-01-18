package pojo;

public class Envio {

    private String idEnvio;
    private String destino;
    private String calle;
    private String numeroGuia;
    private Double costoEnvio;
    private String numero;
    private String colonia;
    private String cp;
    private String ciudad;
    private String estado;
    private String estatus;
    private String historialEstados;
    private Integer idCliente;
    private String nombreCliente;
    private Integer idColaborador;
    private String nombreColaborador;

    public Envio() {
    }

    public Envio(String idEnvio, String destino, String calle, String numeroGuia, 
            Double costoEnvio, String numero, String colonia, String cp, 
            String ciudad, String estado, String estatus, String historialEstados, Integer idCliente, 
            String nombreCliente, Integer idColaborador, String nombreColaborador) {
        this.idEnvio = idEnvio;
        this.destino = destino;
        this.calle = calle;
        this.numeroGuia = numeroGuia;
        this.costoEnvio = costoEnvio;
        this.numero = numero;
        this.colonia = colonia;
        this.cp = cp;
        this.ciudad = ciudad;
        this.estado = estado;
        this.estatus = estatus;
        this.historialEstados = historialEstados;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.idColaborador = idColaborador;
        this.nombreColaborador = nombreColaborador;
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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroGuia() {
        return numeroGuia;
    }

    public void setNumeroGuia(String numeroGuia) {
        this.numeroGuia = numeroGuia;
    }

    public Double getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(Double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(String historialEstados) {
        this.historialEstados = historialEstados;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return numeroGuia;
    }
}
