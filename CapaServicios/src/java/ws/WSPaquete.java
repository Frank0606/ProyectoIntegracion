package ws;

import dominio.ImpPaquetes;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
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
}
