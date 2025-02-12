package ws;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.WSBajaUnidad.class);
        resources.add(ws.WSCliente.class);
        resources.add(ws.WSColaborador.class);
        resources.add(ws.WSEnvio.class);
        resources.add(ws.WSIniciarSesion.class);
        resources.add(ws.WSPaquete.class);
        resources.add(ws.WSRol.class);
        resources.add(ws.WSTipoUnidad.class);
        resources.add(ws.WSUnidad.class);
    }
    
}
