package ws;

import com.google.gson.Gson;
import dominio.ImpEnvios;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import pojo.Envio;
import pojo.Mensaje;

@Path("envio")
public class WSEnvio {
    
    @Context
    private UriInfo context;

    public WSEnvio() {

    }

    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerEnvios() {
        return ImpEnvios.obtenerEnvios();
    }
    
    @Path("numero-guia/{numeroGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerEnvioNoGuia(@PathParam("numeroGuia") String numeroGuia) {
        if (numeroGuia != null && !numeroGuia.isEmpty()) {
            return ImpEnvios.obtenerEnvioNoGuia(numeroGuia);
        }

        throw new BadRequestException();
    }
    
    @Path("agregar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarEnvio(String jsonEnvio) {
        try {
            Gson gson = new Gson();
            Envio envio = gson.fromJson(jsonEnvio, Envio.class);
            return ImpEnvios.registrarEnvio(envio);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
    
    @Path("actualizar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarEnvio(String jsonEnvio) {
        try {
            Gson gson = new Gson();
            Envio envio = gson.fromJson(jsonEnvio, Envio.class);
            return ImpEnvios.actualizarEnvio(envio);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
    
    @Path("eliminar")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje eliminarEnvio(String jsonNumeroGuiaEnvio) {
        try {
            Gson gson = new Gson();
            Envio envio = gson.fromJson(jsonNumeroGuiaEnvio, Envio.class);
            return ImpEnvios.eliminarEnvio(envio.getNumeroGuia());
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
}
