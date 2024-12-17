package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.TipoUnidad;

public class ImpTipoUnidad {


      public static List<TipoUnidad> obtenerTipoUnidades() {
        List<TipoUnidad> respuesta = new ArrayList();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                List<TipoUnidad> listaUnidades = conexionBD.selectList("obtenerTipoUnidades.tipoUnidades");
                if (listaUnidades != null) {
                    for (TipoUnidad tipoUnidad : listaUnidades) {
                        respuesta.add(tipoUnidad);
                    }
                } else {
                    TipoUnidad tipoUnidad = new TipoUnidad();
                    tipoUnidad.setIdTipoUnidad(-1);
                    tipoUnidad.setTipoUnidad("No hay ningun tipo unidad.");
                    respuesta.add(tipoUnidad);
                }
            } catch (Exception e) {
                TipoUnidad tipoUnidad = new TipoUnidad();
                tipoUnidad.setIdTipoUnidad(-1);
                tipoUnidad.setTipoUnidad(e.getMessage());
                respuesta.add(tipoUnidad);
            }
        } else {
            TipoUnidad tipoUnidad = new TipoUnidad();
            tipoUnidad.setIdTipoUnidad(-1);
            tipoUnidad.setTipoUnidad("Error al conectarse a la base de datos.");
            respuesta.add(tipoUnidad);
        }
        return respuesta;
    }
    

     
    public static TipoUnidad obtenerTipoUnidadNombre(String tipoUnidad) {
    SqlSession conexionBD = MybatisUtil.obtenerConexion();
    TipoUnidad respuesta = new TipoUnidad();

    if (conexionBD != null) {
        try {
            // Consultar la unidad directamente
            TipoUnidad unidadDB = conexionBD.selectOne("obtenerTipoUnidades.obtenerTipoUnidadNombre", tipoUnidad);

            if (unidadDB != null) {
                respuesta = unidadDB;
            } else {
                respuesta.setIdTipoUnidad(-1);
                respuesta.setTipoUnidad("No se encuentra el nombre del tipo de unidad.");
            }
        } catch (Exception e) {
            respuesta.setIdTipoUnidad(-1);
            respuesta.setTipoUnidad(e.getMessage());
        } finally {
            conexionBD.close();
        }
    } else {
        respuesta.setIdTipoUnidad(-1);
        respuesta.setTipoUnidad("Error al conectarse a la base de datos.");
    }

    return respuesta;
}

}
