package clienteescritorio.modelo.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.pojo.Unidad;
import clienteescritorio.pojo.RespuestaHTTP;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class UnidadesDAO {

    public static List<Unidad> obtenerUnidad() {
        List<Unidad> unidades = null;
        String url = Constantes.URL_WS + "unidad/todos";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoListaUnidad = new TypeToken<List<Unidad>>() {
                }.getType();
                unidades = gson.fromJson(respuesta.getContenido(), tipoListaUnidad);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }

    public static Mensaje agregarUnidad(Unidad unidad) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "unidad/agregar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(unidad);
            RespuestaHTTP respuesta = ConexionWS.peticionPOSTJSON(url, parametros);
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

    public static Mensaje actualizarUnidad(Unidad unidad) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "unidad/actualizar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(unidad);
            RespuestaHTTP respuesta = ConexionWS.peticionPUTJSON(url, parametros);
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

    public static Mensaje eliminarUnidad(String vin) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "unidad/eliminar/" + vin;
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(vin);
            RespuestaHTTP respuesta = ConexionWS.peticionDELETEJSON(url, parametros);
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
