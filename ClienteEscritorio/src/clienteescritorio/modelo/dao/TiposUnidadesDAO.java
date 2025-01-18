package clienteescritorio.modelo.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.pojo.RespuestaHTTP;
import clienteescritorio.pojo.TipoUnidad;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

public class TiposUnidadesDAO {

    public static List<TipoUnidad> obtenerTiposUnidad() {
        List<TipoUnidad> unidades = null;
        String url = Constantes.URL_WS + "tipoUnidad/todos";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(url);

        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoListaTipoUnidad = new TypeToken<List<TipoUnidad>>() {
                }.getType();
                unidades = gson.fromJson(respuesta.getContenido(), tipoListaTipoUnidad);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }
}
