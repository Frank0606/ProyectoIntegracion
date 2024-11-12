package dominio;

import java.util.ArrayList;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;

public class ImpColaboradores {
    
    public static List<Colaborador> obtenerColaboradores(){
        SqlSession conexionbd = MybatisUtil.obtenerConexion();
        if (conexionbd != null) {
            try {
                List <Colaborador> colaboradores = conexionbd.selectList("ColaboradorMapper.obtenerTodosColaborador");
                conexionbd.commit();
                return colaboradores;
            } catch (Exception e) {
                conexionbd.rollback();
            } finally {
                conexionbd.close();
            }
        } 
        return null;
    }
}
