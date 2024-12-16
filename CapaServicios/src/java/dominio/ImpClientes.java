package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import pojo.Mensaje;

public class ImpClientes {

    public static List<Cliente> obtenerClientes() {
        List<Cliente> respuesta = new ArrayList<>();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                List<Cliente> listaClientes = conexionBD.selectList("ClienteMapper.obtenerTodosClientes");
                if (listaClientes != null && !listaClientes.isEmpty()) {
                    respuesta.addAll(listaClientes);
                } else {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(-1);
                    cliente.setNombreCliente("No hay clientes disponibles.");
                    respuesta.add(cliente);
                }
                conexionBD.commit();
            } catch (Exception e) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(-1);
                cliente.setNombreCliente("Error: " + e.getMessage());
                respuesta.add(cliente);

                conexionBD.rollback();
            } finally {
                conexionBD.close();
            }
        } else {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(-1);
            cliente.setNombreCliente("Error al conectarse a la base de datos.");
            respuesta.add(cliente);
        }
        return respuesta;
    }

    public static Cliente obtenerClientePorCorreo(String correoElectronico) {
        Cliente respuesta = null;
        SqlSession conexionBD = MybatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                respuesta = conexionBD.selectOne("ClienteMapper.obtenerCorreo", correoElectronico);

                if (respuesta == null) {
                    respuesta = new Cliente();
                    respuesta.setIdCliente(-1);
                    respuesta.setNombreCliente("No se encontró ningún cliente con el correo: " + correoElectronico);
                }

                conexionBD.commit();
            } catch (Exception e) {
                respuesta = new Cliente();
                respuesta.setIdCliente(-1);
                respuesta.setNombreCliente("Error: " + e.getMessage());

                conexionBD.rollback();
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta = new Cliente();
            respuesta.setIdCliente(-1);
            respuesta.setNombreCliente("Error al conectarse a la base de datos.");
        }
        return respuesta;
    }

    public static Mensaje registrarCliente(Cliente cliente) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("ClienteMapper.agregar", cliente);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Cliente registrado con éxito.");
                } else {
                    msj.setError(true);
                    msj.setMensaje("No se pudo registrar el cliente. Inténtelo más tarde.");
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

    public static Mensaje actualizarCliente(Cliente cliente) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.update("ClienteMapper.actualizar", cliente);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Cliente actualizado con éxito.");
                } else {
                    Cliente clienteError = new Cliente();
                    clienteError.setIdCliente(-1);
                    clienteError.setNombreCliente("No se encontró el cliente para actualizar.");
                    msj.setError(true);
                    msj.setMensaje(clienteError.getNombreCliente());
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

    public static Mensaje eliminarCliente(String correoElectronico) {
        Mensaje msj = new Mensaje();
        SqlSession conexionBD = MybatisUtil.obtenerConexion();
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.delete("ClienteMapper.eliminar", correoElectronico);
                conexionBD.commit();
                if (resultado > 0) {
                    msj.setError(false);
                    msj.setMensaje("Cliente eliminado con éxito.");
                } else {
                    Cliente clienteError = new Cliente();
                    clienteError.setIdCliente(-1);
                    clienteError.setNombreCliente("No se encontró el cliente para eliminar.");
                    msj.setError(true);
                    msj.setMensaje(clienteError.getNombreCliente());
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
