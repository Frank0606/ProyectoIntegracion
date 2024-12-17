/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio.modelo.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.pojo.RespuestaHTTP;
import clienteescritorio.pojo.Rol;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

/**
 *
 * @author Manzano
 */
public class RolDAO {
    public static List<Rol> obtenerRol(){
        //En la capa de colaboradores, tenemos que tener editar colaborador (El unico que no se tiene que editar es el NoPersonal y el id jajaja) para este servicio tiene que ser en el cuerpo | Hacer el servicio de eliminar (pathparam) (En el metodo eliminar solo tienes que buscar el id para poder borrarlo)
        List<Rol> roles = null;
        String url = Constantes.URL_WS + "roles/obtenerRol";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);
        
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            try{
                Type tipoListaRol = new TypeToken<List<Rol>>() {}.getType();
                roles = gson.fromJson(respuesta.getContenido(), tipoListaRol);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return roles;
    }
}
