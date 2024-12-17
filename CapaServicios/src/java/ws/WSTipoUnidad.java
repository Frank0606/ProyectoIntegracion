package ws;

import dominio.ImpTipoUnidad;
import dominio.ImpUnidad;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Mensaje;
import pojo.TipoUnidad;

@Path("tipo-unidad")
public class WSTipoUnidad {

    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoUnidad> obtenerTipoUnidades() {
        return ImpTipoUnidad.obtenerTipoUnidades();
    }
    
    @Path("nombre-unidad/{tipoUnidad}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TipoUnidad obtenerTipoUnidadNombre(@PathParam("tipoUnidad") String tipoUnidad) {
        if (!tipoUnidad.isEmpty()) {
            return ImpTipoUnidad.obtenerTipoUnidadNombre(tipoUnidad);
        } else {
            throw new BadRequestException("Unidad no valida.");
        }
    }
}
