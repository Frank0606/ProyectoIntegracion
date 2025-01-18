package clienteescritorio.modelo.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.pojo.Cliente;
import clienteescritorio.pojo.RespuestaHTTP;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

public class ClientesDAO {
    
    public static List<Cliente> obtenerClientes() {
        List<Cliente> cliente = null;
        String url = Constantes.URL_WS + "cliente/todos";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try {
                Type tipoListaCliente = new TypeToken<List<Cliente>>(){
                }.getType();
                cliente = gson.fromJson(respuesta.getContenido(), tipoListaCliente);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cliente;
    }
    
     public static Mensaje agregarCliente(Cliente cliente) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "cliente/agregar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(cliente);
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
    
    public static Mensaje actualizarCliente(Cliente cliente) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "cliente/actualizar";
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(cliente);
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
    
    public static Mensaje eliminarCliente(String correoElectronico) {
        Mensaje msj = new Mensaje();
        String url = Constantes.URL_WS + "cliente/eliminar/" + correoElectronico;
        Gson gson = new Gson();
        try {
            String parametros = gson.toJson(correoElectronico);
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
