package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class ImpColaboradores {
    
    public static List<String> obtenerColaboradores(){
        List<String> respuesta = new ArrayList<>();
        SqlSession conexionbd = MybatisUtil.obtenerConexion();
        if (conexionbd != null) {
            try {
                
            } catch (Exception e) {
                
            }
        } else {
            
        }
        return respuesta;
    }
}
