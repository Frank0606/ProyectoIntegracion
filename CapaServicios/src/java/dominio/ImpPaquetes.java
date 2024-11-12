package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
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
                }
            } catch (Exception e) {
                Paquete paquete = new Paquete();
                paquete.setIdPaquete(-1);
                paquete.setDescripcion(e.getMessage());
            }
        } else {
            Paquete paquete = new Paquete();
            paquete.setIdPaquete(-1);
            paquete.setDescripcion("Error al conectarse a la base de datos.");
        }
        return respuesta;
    }
}
