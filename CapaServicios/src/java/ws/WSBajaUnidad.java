package ws;

import com.google.gson.Gson;
import dominio.ImpBajasUnidad;
import dominio.ImpClientes;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import pojo.BajaUnidad;
import pojo.Mensaje;

@Path("baja-unidad")
public class WSBajaUnidad {

    @Context
    private UriInfo context;

    public WSBajaUnidad() {
    }

    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BajaUnidad> obtenerBajasUnidad() {
        return ImpBajasUnidad.obtenerBajasUnidad();
    }

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje obtenerClientes(String jsonBajaUnidad) {
        try {
            Gson gson = new Gson();
            BajaUnidad baja = gson.fromJson(jsonBajaUnidad, BajaUnidad.class);
            return ImpBajasUnidad.registrarBajaUnidad(baja);
        } catch (Exception e) {
            throw new BadRequestException("Error al procesar el registro del cliente.");
        }
    }
}
