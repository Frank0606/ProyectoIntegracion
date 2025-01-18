package clienteescritorio.modelo.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.pojo.Colaborador;
import clienteescritorio.modelo.dao.Mensaje;
import clienteescritorio.pojo.RespuestaHTTP;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ColaboradoresDAO {
        
    public static List<Colaborador> obtenerColaborador() {
        List<Colaborador> colaboradores = null;
        String url = Constantes.URL_WS + "colaborador/todos";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaColaborador = new TypeToken<List<Colaborador>>(){
                }.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
     public static Mensaje agregarColaborador(Colaborador colaborador) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "colaborador/agregar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(colaborador);
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
    
    public static Mensaje actualizarColaborador(Colaborador colaborador) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "colaborador/actualizar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(colaborador);
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
    
    public static Mensaje eliminarColaborador(String noPersonal) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "colaborador/eliminar/" + noPersonal;
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(noPersonal);
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
    
    public static Mensaje asignarUnidad(Colaborador colaborador) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "colaborador/asignar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(colaborador);
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
    
}
