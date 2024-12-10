package ws;

import com.google.gson.Gson;
import dominio.ImpClientes;
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
import pojo.Cliente;
import pojo.Mensaje;

@Path("cliente")
public class WSCliente {

    @Context
    private UriInfo context;

    public WSCliente() {
    }

    @Path("todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerClientes() {
        return ImpClientes.obtenerClientes();
    }

    @Path("correo-electronico/{correoElectronico}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente obtenerClientePorCorreo(@PathParam("correoElectronico") String correoElectronico) {
        if (correoElectronico != null && !correoElectronico.isEmpty()) {
            Cliente cliente = ImpClientes.obtenerClientePorCorreo(correoElectronico);

            if (cliente != null) {
                return cliente;
            }
            throw new BadRequestException("Cliente no encontrado");
        }
        throw new BadRequestException("Correo inválido");
    }

    @Path("agregar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarCliente(String jsonCliente) {
        try {
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(jsonCliente, Cliente.class);
            return ImpClientes.registrarCliente(cliente);
        } catch (Exception e) {
            throw new BadRequestException("Error al procesar el registro del cliente.");
        }
    }

    @Path("actualizar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarCliente(String jsonCliente) {
        try {
            Gson gson = new Gson();
            Cliente cliente = gson.fromJson(jsonCliente, Cliente.class);
            return ImpClientes.actualizarCliente(cliente);
        } catch (Exception e) {
            throw new BadRequestException("Error al procesar la actualización del cliente.");
        }
    }

    @Path("eliminar/{idCliente}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarCliente(@PathParam("idCliente") int idCliente) {
        if (idCliente > 0) {
            return ImpClientes.eliminarCliente(idCliente);
        } else {
            throw new BadRequestException("ID de cliente inválido.");
        }
    }
}
