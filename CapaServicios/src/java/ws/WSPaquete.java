package ws;

import com.google.gson.Gson;
import dominio.ImpPaquetes;
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
import pojo.Mensaje;
import pojo.Paquete;

@Path("paquete")
public class WSPaquete {

    @Context
    private UriInfo context;

    public WSPaquete() {

    }

    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetes() {
        return ImpPaquetes.obtenerPaquetes();
    }

    @Path("id-envio/{idEnvio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaqueteIdEnvio(@PathParam("idEnvio") String idEnvio) {
        if (idEnvio != null && !"".equals(idEnvio)) {
            return ImpPaquetes.obtenerPaqueteIdEnvio(idEnvio);
        }

        throw new BadRequestException();
    }

    @Path("agregar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarPaquete(String jsonPaquete) {
        try {
            Gson gson = new Gson();
            Paquete paquete = gson.fromJson(jsonPaquete, Paquete.class);
            return ImpPaquetes.registrarPaquete(paquete);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Path("actualizar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarPaquete(String jsonPaquete) {
        try {
            Gson gson = new Gson();
            Paquete paquete = gson.fromJson(jsonPaquete, Paquete.class);
            return ImpPaquetes.actualizarPaquete(paquete);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Path("eliminar/{idPaquete}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPaquete(@PathParam("idPaquete") String idPaquete) {
        if (!idPaquete.isEmpty()) {
            return ImpPaquetes.eliminarPaquete(idPaquete);
        } else {
            throw new BadRequestException();
        }
    }
}
