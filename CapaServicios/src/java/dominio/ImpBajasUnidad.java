package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.BajaUnidad;
import pojo.Mensaje;

public class ImpBajasUnidad {
    
    public static List<BajaUnidad> obtenerBajasUnidad() {
        List<BajaUnidad> respuesta = new ArrayList<>();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                List<BajaUnidad> listaBajasUnidad = conexionBD.selectList("BajaUnidadMapper.obtenerTodosBajasUnidad");
                if (listaBajasUnidad != null && !listaBajasUnidad.isEmpty()) {
                    respuesta.addAll(listaBajasUnidad);
                } else {
                    BajaUnidad bajaUnidad = new BajaUnidad();
                    bajaUnidad.setIdBajaUnidad(-1);
                    bajaUnidad.setMotivo("No hay bajas de unidades disponibles.");
                    respuesta.add(bajaUnidad);
                }
                conexionBD.commit();
            } catch (Exception e) {
                BajaUnidad bajaUnidad = new BajaUnidad();
                bajaUnidad.setIdBajaUnidad(-1);
                bajaUnidad.setMotivo("Error: " + e.getMessage());
                respuesta.add(bajaUnidad);

                conexionBD.rollback();
            } finally {
                conexionBD.close();
            }
        } else {
            BajaUnidad bajaUnidad = new BajaUnidad();
            bajaUnidad.setIdBajaUnidad(-1);
            bajaUnidad.setMotivo("Error al conectarse a la base de datos.");
            respuesta.add(bajaUnidad);
        }
        return respuesta;
    }
    
    public static Mensaje registrarBajaUnidad(BajaUnidad bajaUnidad) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("BajaUnidadMapper.agregar", bajaUnidad);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Baja de unidad registrada con éxito.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo registrar la baja de unidad. Inténtelo más tarde.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje("Error: " + e.getMessage());
                conexionBD.rollback();
            } finally {
                conexionBD.close();
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento el servicio no está disponible.");
        }
        return msj;
    }
}