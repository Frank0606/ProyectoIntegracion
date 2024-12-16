package dominio;

import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Rol;

public class ImpRol {

    public static List<Rol> obtencionRol() {
        SqlSession conexionBD = MybatisUtil.obtenerConexion();

        if (conexionBD != null) {
            try {
                List<Rol> roles = conexionBD.selectList("obtenerRol.roles");
                conexionBD.commit();
                return roles;

            } catch (Exception e) {
                e.printStackTrace();
                conexionBD.rollback();

            } finally {
                conexionBD.close();
            }
        }
        return null;
    }
}
