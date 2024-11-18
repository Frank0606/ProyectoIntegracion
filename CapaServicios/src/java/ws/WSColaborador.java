package ws;

import com.google.gson.Gson;
import dominio.ImpColaboradores;
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
import pojo.Colaborador;
import pojo.Mensaje;

@Path("colaborador")
public class WSColaborador {

    @Context
    private UriInfo context;

    public WSColaborador() {

    }

    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradores() {
        return ImpColaboradores.obtenerColaboradores();
    }

    @Path("no-personal")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador obtenerColaboradorPorNoPersonal(@PathParam("noPersonal") String noPersonal) {
        if (noPersonal != null && !noPersonal.isEmpty()) {
            Colaborador colaborador = ImpColaboradores.obtenerColaboradorNoPersonal(noPersonal);

            if (colaborador != null) {
                return colaborador;
            }
            throw new BadRequestException("Colaborador no encontrado");
        }
        throw new BadRequestException("NoPersonal inválido");
    }

    @Path("agregar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarColaborador(String jsonColaborador) {
        try {
            Gson gson = new Gson();
            Colaborador colaborador = gson.fromJson(jsonColaborador, Colaborador.class);
            return ImpColaboradores.registrarColaborador(colaborador);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Path("actualizar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarColaborador(String jsonColaborador) {
        try {
            Gson gson = new Gson();
            Colaborador colaborador = gson.fromJson(jsonColaborador, Colaborador.class);
            return ImpColaboradores.editarColaborador(colaborador);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }

    @Path("Eliminar")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(@PathParam("noPersonal") String noPersonal) {
        return ImpColaboradores.eliminarColaborador(noPersonal);
    }
}
