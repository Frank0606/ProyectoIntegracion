package ws;

import dominio.ImpColaboradores;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("colaborador")
public class WSColaborador {
    
    @Context
    private UriInfo context;
    
    public WSColaborador(){
        
    }
    
    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> obtenerColaboradores(){
        return ImpColaboradores.obtenerColaboradores();
    }
}
