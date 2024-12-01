package ws;

import dominio.ImpRol;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Rol;

@Path("roles")
public class WSRol {

    @Path("obtenerRol")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol> obtencionRol() {
        return ImpRol.obtencionRol();
    }
}
