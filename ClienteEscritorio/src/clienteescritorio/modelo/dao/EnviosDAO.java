package clienteescritorio.modelo.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.pojo.Envio;
import clienteescritorio.pojo.RespuestaHTTP;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

public class EnviosDAO {

    public static List<Envio> obtenerEnvios() {
        List<Envio> envios = null;
        String url = Constantes.URL_WS + "envio/todos";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoListaEnvio = new TypeToken<List<Envio>>() {
                }.getType();
                envios = gson.fromJson(respuesta.getContenido(), tipoListaEnvio);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return envios;
    }

    public static Mensaje agregarEnvio(Envio envio) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "envio/agregar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(envio);
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

    public static Mensaje actualizarEnvio(Envio envio) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "envio/actualizar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(envio);
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

    public static Mensaje eliminarEnvio(String numeroGuia) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "envio/eliminar/" + numeroGuia;
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(numeroGuia);
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
    
    public static Mensaje cambiarEstatusEnvio(Envio envio) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "envio/estatus";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(envio);
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
}
