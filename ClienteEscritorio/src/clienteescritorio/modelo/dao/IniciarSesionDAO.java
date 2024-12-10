package clienteescritorio.modelo.dao;

import clienteescritorio.modelo.ConexionWS;
import clienteescritorio.pojo.Colaborador;
import clienteescritorio.pojo.IniciarSesion;
import clienteescritorio.pojo.RespuestaHTTP;
import clienteescritorio.utilidades.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;

public class IniciarSesionDAO {
    
    public static IniciarSesion iniciarSesion(String noPersonal, String contrasenia) {

        IniciarSesion respuestaLogin = new IniciarSesion();
        Gson gson = new Gson();
        String urlServicio = Constantes.URL_WS + "iniciar-sesion/colaborador";
        Colaborador colaborador = new Colaborador();
        colaborador.setNoPersonal(noPersonal);
        colaborador.setContrasenia(contrasenia);
        String parametros = gson.toJson(colaborador);

        RespuestaHTTP respuestaWS = ConexionWS.peticionPOSTJson(urlServicio, parametros);
        
        if(respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            respuestaLogin = gson.fromJson(respuestaWS.getContenido(), IniciarSesion.class);
        } else {
            respuestaLogin.setError(true);
            respuestaLogin.setMensaje("Lo sentimos, hubo un error al iniciar sesión. Intenta de nuevo. :(");
        }

        return respuestaLogin;
    }
}
