package es.tfg.atenea.core.login;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioHelper {
    
    private UsuarioHelper(){
        
    }
    
    public static Usuario getUsuario(Connection conexion, BigDecimal idUsuario)throws SQLException{
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setBigDecimal(1, idUsuario);
            try(ResultSet rs = pst.executeQuery()){
                return rs.next() ? new Usuario(rs) : null;
            }
        }
    }
    
    public static BigDecimal getUsuarioAutenticado(Connection conexion, String nombreUsuario, String password)throws SQLException{
        String sql = "SELECT id_usuario FROM usuario WHERE nombre_usuario = ? AND password = ?";
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setString(1, nombreUsuario);
            pst.setString(2, password);
            try(ResultSet rs = pst.executeQuery()){
                return rs.next() ? rs.getBigDecimal(1) : null;
            }
        }
    }
    
}
