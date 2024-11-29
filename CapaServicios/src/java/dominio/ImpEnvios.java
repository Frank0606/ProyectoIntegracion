package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.Mensaje;

public class ImpEnvios {

    public static List<Envio> obtenerEnvios() {
        List<Envio> respuesta = new ArrayList();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                List<Envio> listaEnvios = conexionBD.selectList("envio.obtenerEnvios");
                if (listaEnvios != null) {
                    for (Envio envio : listaEnvios) {
                        respuesta.add(envio);
                    }
                } else {
                    Envio envio = new Envio();
                    envio.setIdEnvio("-1");
                    envio.setNumeroGuia("No hay envios.");
                    respuesta.add(envio);
                }
            } catch (Exception e) {
                Envio envio = new Envio();
                envio.setIdEnvio("-1");
                envio.setNumeroGuia(e.getMessage());
                respuesta.add(envio);
            }
        } else {
            Envio envio = new Envio();
            envio.setIdEnvio("-1");
            envio.setNumeroGuia("Error al conectarse a la base de datos.");
            respuesta.add(envio);
        }
        return respuesta;
    }

    public static List<Envio> obtenerEnvioNoGuia(String numeroGuia) {
        List<Envio> respuesta = new ArrayList();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                Envio envioDB = conexionBD.selectOne("envio.obtenerEnvioNoGuia", numeroGuia);
                if (envioDB != null) {
                    respuesta.add(envioDB);
                } else {
                    Envio envio = new Envio();
                    envio.setIdEnvio("-1");
                    envio.setNumeroGuia("No hay envios.");
                    respuesta.add(envio);
                }
            } catch (Exception e) {
                Envio envio = new Envio();
                envio.setIdEnvio("-1");
                envio.setNumeroGuia(e.getMessage());
                respuesta.add(envio);
            }
        } else {
            Envio envio = new Envio();
            envio.setIdEnvio("-1");
            envio.setNumeroGuia("Error al conectarse a la base de datos.");
            respuesta.add(envio);
        }
        return respuesta;
    }

    public static Mensaje registrarEnvio(Envio envio) {
        Mensaje msj = new Mensaje();
        SqlSession conexionbd = MybatisUtil.obtenerConexion();
        if (conexionbd != null) {
            try {
                int resultado = conexionbd.insert("envio.agregarEnvio", envio);
                conexionbd.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se agrego un envio exitosamente.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo agregar el envio. Intentalo de nuevo.");
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

    public static Mensaje actualizarEnvio(Envio envio) {
        Mensaje msj = new Mensaje();
        SqlSession conexionbd = MybatisUtil.obtenerConexion();
        if (conexionbd != null) {
            try {
                int resultado = conexionbd.update("envio.actualizarEnvio", envio);
                conexionbd.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se actualizo el envio correctamente.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo acualizar el envio. Intentalo de nuevo.");
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

    public static Mensaje eliminarEnvio(String numeroGuia) {
        Mensaje msj = new Mensaje();
        SqlSession conexionbd = MybatisUtil.obtenerConexion();
        if (conexionbd != null) {
            try {
                int resultado = conexionbd.delete("envio.eliminarEnvio", numeroGuia);
                conexionbd.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Se elimino al envio correctamente.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo eliminar al envio. Intentalo de nuevo.");
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
