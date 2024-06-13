package es.tfg.atenea.core.banco;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RespuestaHelper {
    
    private RespuestaHelper(){
        
    }
    
    public static List<Respuesta> getRespuestaPregunta(Connection conexion, BigDecimal idPregunta) throws SQLException{
        List<Respuesta> respuestas = new ArrayList<>();
        String sql = "SELECT * FROM respuesta WHERE id_pregunta = ? ";
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setBigDecimal(1, idPregunta);
            try(ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    respuestas.add(new Respuesta(rs));
                }
            }
        }
        return respuestas;
    }
    
}
