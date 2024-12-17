/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio.modelo.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.pojo.Mensaje;
import clienteescritorio.pojo.RespuestaHTTP;
import clienteescritorio.pojo.Unidad;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

/**
 *
 * @author Eric Jair
 */
public class UnidadesDAO {
    
    
    public static List<Unidad> obtenerUnidades() {

        List<Unidad> unidades = null;
        String url = Constantes.URL_WS + "unidad/todos";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
 
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();

            try {

                Type tipoListUnidad = new TypeToken<List<Unidad>>() {
                }.getType();
                unidades = gson.fromJson(respuesta.getContenido(), tipoListUnidad);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return unidades;
    }
    
    public static Unidad UnidadVin(String vin) {

        Unidad respuestaUnidadVin = new Unidad();
        Gson gson = new Gson();
        String urlServicio = Constantes.URL_WS + "unidad/obtenerUnidadVin";
        Unidad unidad = new Unidad();
        unidad.setVin(vin);
        
        String parametros = gson.toJson(unidad);

        RespuestaHTTP respuestaWS = ConexionWS.peticionPOSTJson(urlServicio, parametros);
        
        if(respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            respuestaUnidadVin = gson.fromJson(respuestaWS.getContenido(), Unidad.class);
        } else {
            respuestaUnidadVin.setIdUnidad(-1);
            respuestaUnidadVin.setVin("Lo sentimos, hubo un error al iniciar sesión. Intenta de nuevo. :(");
        }   

        return respuestaUnidadVin;
    }
    
    
      public static Mensaje registrarUnidad(Unidad unidad){
        Mensaje msj = new Mensaje();
        String urlServicio = Constantes.URL_WS+"unidad/agregar";
        Gson gson = new Gson();
        
        try{
        String parametros = gson.toJson(unidad);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPOSTJson(urlServicio, parametros);
        
        if(respuestaWS.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
        msj.setError(true);
        msj.setMensaje(respuestaWS.getContenido());
        
        }else {
         msj.setError(true);
         msj.setMensaje(respuestaWS.getContenido());
        }
        
        }catch(Exception e){
        msj.setError(true);
        msj.setMensaje(e.getMessage());
        
        }
    return msj;
    }
    
     public static Mensaje editarUnidad(Unidad unidad) {
        Mensaje msj = new Mensaje();
        String urlServicio = Constantes.URL_WS + "unidad/actualizar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(unidad);
            RespuestaHTTP respuesta = ConexionWS.peticionPUTJSON(urlServicio, parametros);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                msj.setError(true);
                msj.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            msj.setError(true);
            msj.setMensaje(e.getMessage());
        }
        return msj;
    }
     
     public static Mensaje eliminarUnidad(int vin) {
        Mensaje msj = new Mensaje();
        String urlServicio = Constantes.URL_WS + "unidad/eliminar" + vin;
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(vin);
            RespuestaHTTP respuesta = ConexionWS.peticionDELETEJSON(urlServicio, parametros);
            if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
                msj = gson.fromJson(respuesta.getContenido(), Mensaje.class);
            } else {
                msj.setError(true);
                msj.setMensaje(respuesta.getContenido());
            }
        } catch (Exception e) {
            msj.setError(true);
            msj.setMensaje(e.getMessage());
        }
        return msj;
    }
       
      
}
