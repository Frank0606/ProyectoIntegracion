package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import pojo.Colaborador;
import pojo.IniciarSesionCliente;
import pojo.IniciarSesionColaborador;

public class ImpIniciarSesion {

    public static IniciarSesionColaborador validarSesionColaborador(String noPersonal, String contrasenia) {
        IniciarSesionColaborador respuesta = new IniciarSesionColaborador();
        SqlSession conexionbd = MybatisUtil.obtenerConexion();
        if (conexionbd != null) {
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("contrasenia", contrasenia);
                Colaborador colaborador = conexionbd.selectOne("sesion.iniciarSesionColaborador", parametros);
                if (colaborador != null) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales correctas del colaborador " + colaborador.getNombreColaborador());
                    respuesta.setColaborador(colaborador);
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("Número de personal y/o contraseña incorrectos.");
                }
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionbd.close(); // Cierre de conexión
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No se pudo consultar la información en este momento.");
        }
        return respuesta;
    }

    public static IniciarSesionCliente validarSesionCliente(String correo, String contrasenia) {
        IniciarSesionCliente respuesta = new IniciarSesionCliente();
        SqlSession conexionbd = MybatisUtil.obtenerConexion();
        if (conexionbd != null) {
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("correo", correo);
                parametros.put("contrasenia", contrasenia);
                Cliente cliente = conexionbd.selectOne("sesion.iniciarSesionCliente", parametros);
                if (cliente != null) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales correctas del cliente " + cliente.getNombreCliente());
                    respuesta.setCliente(cliente);
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("Correo y/o contraseña incorrectos.");
                }
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionbd.close(); // Cierre de conexión
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No se pudo consultar la información en este momento.");
        }
        return respuesta;
    }

    public static IniciarSesionColaborador validarSesionConductor(String noPersonal, String contrasenia) {
        IniciarSesionColaborador respuesta = new IniciarSesionColaborador();
        SqlSession conexionbd = MybatisUtil.obtenerConexion();
        if (conexionbd != null) {
            try {
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("contrasenia", contrasenia);
                Colaborador colaborador = conexionbd.selectOne("sesion.iniciarSesionConductor", parametros);
                if (colaborador != null) {
                    respuesta.setError(false);
                    respuesta.setMensaje("Credenciales correctas del conductor " + colaborador.getNombreColaborador());
                    respuesta.setColaborador(colaborador);
                } else {
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, no cuentas con los permisos necesarios. No eres conductor.");
                }
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionbd.close(); // Cierre de conexión
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("No se pudo consultar la información en este momento.");
        }
        return respuesta;
    }
}