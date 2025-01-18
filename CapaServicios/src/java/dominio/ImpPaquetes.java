package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.Paquete;

public class ImpPaquetes {

    public static List<Paquete> obtenerPaquetes() {
        List<Paquete> respuesta = new ArrayList<>();
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
                    paquete.setIdPaquete("-1");
                    paquete.setDescripcion("No hay paquetes.");
                    respuesta.add(paquete);
                }
            } catch (Exception e) {
                Paquete paquete = new Paquete();
                paquete.setIdPaquete("-1");
                paquete.setDescripcion(e.getMessage());
                respuesta.add(paquete);
            } finally {
                conexionBD.close(); // Cierre de conexión
            }
        } else {
            Paquete paquete = new Paquete();
            paquete.setIdPaquete("-1");
            paquete.setDescripcion("Error al conectarse a la base de datos.");
            respuesta.add(paquete);
        }
        return respuesta;
    }

    public static List<Paquete> obtenerPaqueteIdEnvio(String idEnvio) {
        List<Paquete> respuesta = new ArrayList<>();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                List<Paquete> paqueteDB = conexionBD.selectList("paquete.obtenerPaqueteIdEnvio", idEnvio);
                if (paqueteDB != null) {
                    for (Paquete paquete : paqueteDB) {
                        respuesta.add(paquete);
                    }
                } else {
                    Paquete paquete = new Paquete();
                    paquete.setIdPaquete("-1");
                    paquete.setDescripcion("No hay paquetes.");
                    respuesta.add(paquete);
                }
            } catch (Exception e) {
                Paquete paquete = new Paquete();
                paquete.setIdPaquete("-1");
                paquete.setDescripcion(e.getMessage());
                respuesta.add(paquete);
            } finally {
                conexionBD.close(); // Cierre de conexión
            }
        } else {
            Paquete paquete = new Paquete();
            paquete.setIdPaquete("-1");
            paquete.setDescripcion("Error al conectarse a la base de datos.");
            respuesta.add(paquete);
        }
        return respuesta;
    }

    public static Mensaje registrarPaquete(Paquete paquete) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("paquete.agregarPaquete", paquete);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se agregó un paquete exitosamente.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo agregar el paquete. Inténtalo de nuevo.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            } finally {
                conexionBD.close(); // Cierre de conexión
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no está disponible el servicio. Inténtelo más tarde.");
        }
        return msj;
    }

    public static Mensaje actualizarPaquete(Paquete paquete) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("paquete.actualizarPaquete", paquete);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se actualizó el paquete correctamente.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo actualizar el paquete. Inténtalo de nuevo.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            } finally {
                conexionBD.close(); // Cierre de conexión
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no está disponible el servicio. Inténtelo más tarde.");
        }
        return msj;
    }

    public static Mensaje eliminarPaquete(String idPaquete) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("paquete.eliminarPaquete", idPaquete);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se eliminó el paquete correctamente.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo eliminar el paquete. Inténtalo de nuevo.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            } finally {
                conexionBD.close(); // Cierre de conexión
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento no está disponible el servicio. Inténtelo más tarde.");
        }
        return msj;
    }
}