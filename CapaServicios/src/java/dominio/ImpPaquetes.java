package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.Paquete;

public class ImpPaquetes {

    public static List<Paquete> obtenerPaquetes() {
        List<Paquete> respuesta = new ArrayList();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                List<Paquete> listaPaquetes = conexionBD.selectList("paquete.obtenerPaquetes");
                if (listaPaquetes != null) {
                    for (Paquete paquete : listaPaquetes) {
                        respuesta.add(paquete);
                    }
                } else {
                    Paquete paquete = new Paquete();
                    paquete.setIdPaquete(-1);
                    paquete.setDescripcion("No hay paquetes.");
                    respuesta.add(paquete);
                }
            } catch (Exception e) {
                Paquete paquete = new Paquete();
                paquete.setIdPaquete(-1);
                paquete.setDescripcion(e.getMessage());
                respuesta.add(paquete);
            }
        } else {
            Paquete paquete = new Paquete();
            paquete.setIdPaquete(-1);
            paquete.setDescripcion("Error al conectarse a la base de datos.");
            respuesta.add(paquete);
        }
        return respuesta;
    }

    public static List<Paquete> obtenerPaqueteId(Integer idPaquete) {
        List<Paquete> respuesta = new ArrayList();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                Paquete paqueteDB = conexionBD.selectOne("paquete.obtenerPaqueteId", idPaquete);
                if (paqueteDB != null) {
                    respuesta.add(paqueteDB);
                } else {
                    Paquete paquete = new Paquete();
                    paquete.setIdPaquete(-1);
                    paquete.setDescripcion("No hay paquetes.");
                    respuesta.add(paquete);
                }
            } catch (Exception e) {
                Paquete paquete = new Paquete();
                paquete.setIdPaquete(-1);
                paquete.setDescripcion(e.getMessage());
                respuesta.add(paquete);
            }
        } else {
            Paquete paquete = new Paquete();
            paquete.setIdPaquete(-1);
            paquete.setDescripcion("Error al conectarse a la base de datos.");
            respuesta.add(paquete);
        }
        return respuesta;
    }

    public static Mensaje registrarPaquete(Paquete paquete) {
        Mensaje msj = new Mensaje();
        SqlSession conexionbd = MybatisUtil.obtenerConexion();
        if (conexionbd != null) {
            try {
                int resultado = conexionbd.insert("paquete.agregarPaquete", paquete);
                conexionbd.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se agrego un paquete exitosamente.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo agregar el paquete. Intentalo de nuevo.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no esta disponible el servicio. Intentelo mas tarde.");
        }

        return msj;
    }

    public static Mensaje actualizarPaquete(Paquete paquete) {
        Mensaje msj = new Mensaje();
        SqlSession conexionbd = MybatisUtil.obtenerConexion();
        if (conexionbd != null) {
            try {
                int resultado = conexionbd.update("paquete.actualizarPaquete", paquete);
                conexionbd.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se actualizo el paquete correctamente.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo aztualizar el paquete. Intentalo de nuevo.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no esta disponible el servicio. Intentelo mas tarde.");
        }

        return msj;
    }

    public static Mensaje eliminarPaquete(Integer idPaquete) {
        Mensaje msj = new Mensaje();
        SqlSession conexionbd = MybatisUtil.obtenerConexion();
        if (conexionbd != null) {
            try {
                int resultado = conexionbd.delete("paquete.eliminarPaquete", idPaquete);
                conexionbd.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se elimino al paquete correctamente.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo eliminar al paquete. Intentalo de nuevo.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no esta disponible el servicio. Intentelo mas tarde.");
        }

        return msj;
    }
}
