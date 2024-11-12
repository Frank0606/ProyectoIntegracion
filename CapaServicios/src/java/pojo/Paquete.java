package pojo;

public class Paquete {
    
    private Integer idPaquete;
    private String descripcion;
    private Float peso;
    private Float profundidad;
    private Float alto;
    private Float ancho;
    private Integer idEnvio;

    public Paquete() {
    }

    public Paquete(Integer idPaquete, String descripcion, Float peso, Float profundidad, Float alto, Float ancho, Integer idEnvio) {
        this.idPaquete = idPaquete;
        this.descripcion = descripcion;
        this.peso = peso;
        this.profundidad = profundidad;
        this.alto = alto;
        this.ancho = ancho;
        this.idEnvio = idEnvio;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Float profundidad) {
        this.profundidad = profundidad;
    }

    public Float getAlto() {
        return alto;
    }

    public void setAlto(Float alto) {
        this.alto = alto;
    }

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public Integer getidEnvio() {
        return idEnvio;
    }

    public void setidEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    @Override
    public String toString() {
        return "Paquete{" + "idPaquete=" + idPaquete + ", descripcion=" + descripcion + ", peso=" + peso + ", profundidad=" + profundidad + ", alto=" + alto + ", ancho=" + ancho + ", idEnvio=" + idEnvio + '}';
    }
}
