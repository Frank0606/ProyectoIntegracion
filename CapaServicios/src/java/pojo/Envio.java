package pojo;

public class Envio {

    private Integer idEnvio;
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
    private Integer idCliente;

    public Envio() {
    }

    public Envio(Integer idEnvio, String origen, String calle, String numeroGuia, Double costoEnvio, String numeroCasa, String colonia, String cp, String ciudad, String estado, String estatus, Integer idCliente) {
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
        this.idCliente = idCliente;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
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

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Envio{" + "idEnvio=" + idEnvio + ", origen=" + origen + ", calle=" + calle + ", numeroGuia=" + numeroGuia + ", costoEnvio=" + costoEnvio + ", numeroCasa=" + numeroCasa + ", colonia=" + colonia + ", cp=" + cp + ", ciudad=" + ciudad + ", estado=" + estado + ", estatus=" + estatus + ", idCliente=" + idCliente + '}';
    }
}
