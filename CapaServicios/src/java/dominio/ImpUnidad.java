package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.Unidad;

public class ImpUnidad {



     public static List<Unidad> obtenerUnidades() {
        List<Unidad> respuesta = new ArrayList();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                List<Unidad> listaUnidades = conexionBD.selectList("unidad.unidades");
                if (listaUnidades != null) {
                    for (Unidad unidad : listaUnidades) {
                        respuesta.add(unidad);
                    }
                } else {
                    Unidad unidad = new Unidad();
                    unidad.setIdUnidad(-1);
                    unidad.setVin("No hay ningun tipo unidad.");
                    respuesta.add(unidad);
                }
                
            } catch (Exception e) {
                Unidad unidad = new Unidad();
                unidad.setIdUnidad(-1);
                unidad.setVin(e.getMessage());
                respuesta.add(unidad);
            }
        } else {
            Unidad unidad = new Unidad();
            unidad.setIdUnidad(-1);
            unidad.setVin("Error al conectarse a la base de datos.");
            respuesta.add(unidad);
        }
        return respuesta;
    }
    

    
    public static Unidad obtenerUnidadVin(String vin) {
    SqlSession conexionBD = MybatisUtil.obtenerConexion();
    Unidad respuesta = new Unidad();

    if (conexionBD != null) {
        try {
            // Consultar la unidad directamente
            Unidad unidadDB = conexionBD.selectOne("unidad.unidadVin", vin);
            System.out.println("que pedo carnal"+unidadDB);
            if (unidadDB != null) {
                respuesta = unidadDB;
            } else {
                respuesta.setIdUnidad(-1);
                respuesta.setVin("No se encuentra el nombre del tipo de unidad.");
            }
        } catch (Exception e) {
            respuesta.setIdUnidad(-1);
            respuesta.setVin(e.getMessage());
        } finally {
            conexionBD.close();
        }
    } else {
        respuesta.setIdUnidad(-1);
        respuesta.setVin("Error al conectarse a la base de datos.");
    }

    return respuesta;
}

    public static Mensaje agregarUnidad(Unidad unidad) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("unidad.registrarUnidad", unidad);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Unidad registrada con exito");

                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo realizar el registro, intentelo mas tarde");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            }
        } else {
            msj.setError(true);
            msj.setMensaje("\"Por el momento el servicio no esta disponible.\"");
        }
        return msj;
    }

    public static Mensaje actualizarUnidad(Unidad unidad) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("unidad.actualizarUnidad", unidad);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Unidad actualizada con exito.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo actualizar la unidad, inténtelo más tarde.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento el servicio no esta disponible.");
        }
        return msj;
    }

    public static Mensaje eliminarUnidad(String vin) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("unidad.eliminarUnidad", vin);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Unidad se elimino con exito.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Error al eliminar la unidad, inténtelo más tarde.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento el servicio no esta disponible.");
        }
        return msj;
    }

}
