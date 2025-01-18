package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Mensaje;

public class ImpColaboradores {

    public static List<Colaborador> obtenerColaboradores() {
        List<Colaborador> respuesta = new ArrayList<>();
        SqlSession conexionbd = MybatisUtil.obtenerConexion();

        if (conexionbd != null) {
            try {
                List<Colaborador> listaColaboradores = conexionbd.selectList("ColaboradorMapper.obtenerTodosColaborador");
                if (listaColaboradores != null && !listaColaboradores.isEmpty()) {
                    respuesta.addAll(listaColaboradores);
                } else {
                    Colaborador colaborador = new Colaborador();
                    colaborador.setIdColaborador(-1);
                    colaborador.setNombreColaborador("No hay colaboradores disponibles.");
                    respuesta.add(colaborador);
                }
                conexionbd.commit();
            } catch (Exception e) {
                Colaborador colaborador = new Colaborador();
                colaborador.setIdColaborador(-1);
                colaborador.setNombreColaborador("Error: " + e.getMessage());
                respuesta.add(colaborador);

                conexionbd.rollback();
            } finally {
                conexionbd.close(); // Cierre de conexión
            }
        } else {
            Colaborador colaborador = new Colaborador();
            colaborador.setIdColaborador(-1);
            colaborador.setNombreColaborador("Error al conectarse a la base de datos.");
            respuesta.add(colaborador);
        }
        return respuesta;
    }

    public static Colaborador obtenerColaboradorNoPersonal(String noPersonal) {
        Colaborador respuesta = null;
        SqlSession conexionBD = MybatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                respuesta = conexionBD.selectOne("ColaboradorMapper.obtenerNoPersonal", noPersonal);

                if (respuesta == null) {
                    respuesta = new Colaborador();
                    respuesta.setIdColaborador(-1);
                    respuesta.setNombreColaborador("No se encontró ningún colaborador con NoPersonal: " + noPersonal);
                }

                conexionBD.commit();
            } catch (Exception e) {
                respuesta = new Colaborador();
                respuesta.setIdColaborador(-1);
                respuesta.setNombreColaborador("Error: " + e.getMessage());

                conexionBD.rollback();
            } finally {
                conexionBD.close(); // Cierre de conexión
            }
        } else {
            respuesta = new Colaborador();
            respuesta.setIdColaborador(-1);
            respuesta.setNombreColaborador("Error al conectarse a la base de datos.");
        }
        return respuesta;
    }

    public static Mensaje registrarColaborador(Colaborador colaborador) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("ColaboradorMapper.agregar", colaborador);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Colaborador(a) registrado con exito.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo registrar al colaborador(a), inténtelo más tarde.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            } finally {
                conexionBD.close(); // Cierre de conexión
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento el servicio no esta disponible.");
        }
        return msj;
    }

    public static Mensaje editarColaborador(Colaborador colaborador) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("ColaboradorMapper.actualizar", colaborador);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Colaborador(a) actualizado con exito.");
                } else {
                    msj.setError(true);
                    //msj.setMensaje("No se pudo actualizar al colaborador(a), inténtelo más tarde.");
                    msj.setMensaje("resultado: " + String.valueOf(resultado));
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            } finally {
                conexionBD.close(); // Cierre de conexión
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento el servicio no esta disponible.");
        }
        return msj;
    }

    public static Mensaje eliminarColaborador(String noPersonal) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("ColaboradorMapper.eliminar", noPersonal);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Colaborador(a) eliminado con exito.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo eliminar al colaborador(a), inténtelo más tarde.");
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            } finally {
                conexionBD.close(); // Cierre de conexión
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento el servicio no esta disponible.");
        }
        return msj;
    }

    public static Mensaje asignarUnidad(Colaborador colaborador) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                if (colaborador.getNoPersonal().equals("Liberar")) {
                    int resultado = conexionBD.update("ColaboradorMapper.desasignarUnidad", colaborador.getIdUnidad());
                    conexionBD.commit();
                    if (resultado > 0) {
                        msj.setError(false);
                        msj.setMensaje("Unidad liberada con éxito");
                    } else {
                        msj.setError(true);
                        msj.setMensaje("No se pudo liberar la unidad, intentelo más tarde.");
                    }
                } else {
                    int count = conexionBD.selectOne("ColaboradorMapper.verificarUnidad", colaborador.getIdUnidad());
                    if (count > 0) {
                        msj.setError(true);
                        msj.setMensaje("El idUnidad ya está asignado a otro colaborador.");
                    } else {
                        int resultado = conexionBD.update("ColaboradorMapper.asignarUnidad", colaborador);
                        conexionBD.commit();
                        if (resultado > 0) {
                            msj.setError(false);
                            msj.setMensaje("Unidad asignada con éxito");
                        } else {
                            msj.setError(true);
                            msj.setMensaje("No se pudo realizar la asignación, intentelo más tarde.");
                        }
                    }
                }
            } catch (Exception e) {
                msj.setError(true);
                msj.setMensaje(e.getMessage());
            } finally {
                conexionBD.close(); // Cierre de conexión
            }
        } else {
            msj.setError(true);
            msj.setMensaje("Por el momento el servicio no está disponible.");
        }
        return msj;
    }

    public static byte[] obtenerFoto(String noPersonal) {
        byte[] foto = null;
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                foto = conexionBD.selectOne("ColaboradorMapper.obtenerFoto", noPersonal);
                if (foto == null) {
                    foto = new byte[0]; // O cualquier valor predeterminado
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close(); // Cierre de conexión
            }
        }
        return foto;
    }

    public static boolean actualizarFoto(String noPersonal, byte[] foto) {
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                Colaborador colaborador = new Colaborador();
                colaborador.setNoPersonal(noPersonal);
                colaborador.setFotografia(foto);
                int filasActualizadas = conexionBD.update("ColaboradorMapper.actualizarFoto", colaborador);
                conexionBD.commit();
                return filasActualizadas > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                conexionBD.close(); // Cierre de conexión 
            }
        } else {
            return false;
        }
    }
}
