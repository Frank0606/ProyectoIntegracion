/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio.modelo.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.pojo.RespuestaHTTP;
import clienteescritorio.pojo.TipoUnidad;
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
public class TipoUnidadesDAO {

    public static List<TipoUnidad> obtenerTipoUnidades() {

        List<TipoUnidad> tipoUnidades = null;
        String url = Constantes.URL_WS + "tipo-unidad/todos";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();

            try {

                Type tipoListTipoUnidad = new TypeToken<List<TipoUnidad>>() {
                }.getType();
                tipoUnidades = gson.fromJson(respuesta.getContenido(), tipoListTipoUnidad);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return tipoUnidades;
    }
     public static TipoUnidad tipoUnidadNombre(String tipoUnidad) {

        TipoUnidad respuestaTipoUnidadNombre = new TipoUnidad();
        Gson gson = new Gson();
        String urlServicio = Constantes.URL_WS + "tipo-unidad/nombre-unidad";
        TipoUnidad unidadTipo = new TipoUnidad();
        unidadTipo.setTipoUnidad(tipoUnidad);
        
        String parametros = gson.toJson(unidadTipo);

        RespuestaHTTP respuestaWS = ConexionWS.peticionPOSTJson(urlServicio, parametros);
        
        if(respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            respuestaTipoUnidadNombre = gson.fromJson(respuestaWS.getContenido(), TipoUnidad.class);
        } else {
            respuestaTipoUnidadNombre.setIdTipoUnidad(-1);
            respuestaTipoUnidadNombre.setTipoUnidad("Lo sentimos, hubo un error al encontrar la unidad. Intenta de nuevo. :(");
        }   

        return respuestaTipoUnidadNombre;
    }
}
