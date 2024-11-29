package pojo;

public class Envio {

    private String idEnvio;
    private String origen;
    private String calle;
    private String numeroGuia;
    private Double costoEnvio;
    private String numeroCasa;
    private String colonia;
    private String cp;
    private String ciudad;
    private String estado;
    private String estatus;
    private String historialEstados;
    private String idCliente;

    public Envio() {
    }

    public Envio(String idEnvio, String origen, String calle, String numeroGuia, 
            Double costoEnvio, String numeroCasa, String colonia, String cp, 
            String ciudad, String estado, String estatus, String historialEstados, String idCliente) {
        this.idEnvio = idEnvio;
        this.origen = origen;
        this.calle = calle;
        this.numeroGuia = numeroGuia;
        this.costoEnvio = costoEnvio;
        this.numeroCasa = numeroCasa;
        this.colonia = colonia;
        this.cp = cp;
        this.ciudad = ciudad;
        this.estado = estado;
        this.estatus = estatus;
        this.historialEstados = historialEstados;
        this.idCliente = idCliente;
    }

    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
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

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
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

    public String getIdCliente() {
        return idCliente;
    }

    public String getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(String historialEstados) {
        this.historialEstados = historialEstados;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Envio{" + "idEnvio=" + idEnvio + ", origen=" + origen + ", calle=" + calle + ", numeroGuia=" + numeroGuia + ", costoEnvio=" + costoEnvio + ", numeroCasa=" + numeroCasa + ", colonia=" + colonia + ", cp=" + cp + ", ciudad=" + ciudad + ", estado=" + estado + ", estatus=" + estatus + ", idCliente=" + idCliente + '}';
    }
}
