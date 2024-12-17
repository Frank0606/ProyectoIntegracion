/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio.pojo;

/**
 *
 * @author Eric Jair
 */
public class TipoUnidad {
    
    private Integer idTipoUnidad;
    private String tipoUnidad;

    
     public TipoUnidad() {
        
    }
     
    public TipoUnidad(Integer idTipoUnidad, String tipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
        this.tipoUnidad = tipoUnidad;
    }

    public Integer getIdTipoUnidad() {
        return idTipoUnidad;
    }

    public void setIdTipoUnidad(Integer idTipoUnidad) {
        this.idTipoUnidad = idTipoUnidad;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }
    
    
}
