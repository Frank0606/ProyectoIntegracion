package ws;

import com.google.gson.Gson;
import dominio.ImpIniciarSesion;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import pojo.Cliente;
import pojo.Colaborador;
import pojo.IniciarSesionCliente;
import pojo.IniciarSesionColaborador;

@Path("iniciar-sesion")
public class WSIniciarSesion {

    @Context //Accedemos a la uri para acceder a un valor (Por si quieres otra informacion en la peticion)
    private UriInfo context;

    // Se diferencian por ruta o por peticion.
    public WSIniciarSesion() {
    }

    @Path("colaborador")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IniciarSesionColaborador iniciarSesion(String jsonCredencialesColaborador) {
        Gson gson = new Gson();
        Colaborador colaborador = gson.fromJson(jsonCredencialesColaborador, Colaborador.class);
        if ((colaborador.getNoPersonal() != null && !colaborador.getNoPersonal().isEmpty()) 
                && (colaborador.getContrasenia() != null && !colaborador.getContrasenia().isEmpty()) 
                && colaborador.getNoPersonal().length() <= 10) {
            return ImpIniciarSesion.validarSesionColaborador(colaborador.getNoPersonal(), colaborador.getContrasenia());
        }

        throw new BadRequestException();
    }

    @Path("cliente")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public IniciarSesionCliente iniciarSesionCliente(String jsonCredencialesCliente) {
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(jsonCredencialesCliente, Cliente.class);
        if ((cliente.getCorreoElectronico() != null && !cliente.getCorreoElectronico().isEmpty()) 
                && (cliente.getContrasenia() != null && !cliente.getContrasenia().isEmpty())) {
            return ImpIniciarSesion.validarSesionCliente(cliente.getCorreoElectronico(), cliente.getContrasenia());
        }

        throw new BadRequestException();
    }
}
