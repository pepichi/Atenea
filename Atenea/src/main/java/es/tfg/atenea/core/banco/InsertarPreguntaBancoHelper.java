package es.tfg.atenea.core.banco;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InsertarPreguntaBancoHelper {

    private InsertarPreguntaBancoHelper() {

    }

    public static BigDecimal insertarEnunciado(Connection conexion, Pregunta pregunta) throws SQLException {
        String sql = "  INSERT INTO pregunta (enunciado, fuente_pregunta, comentarios) "
                + "     VALUES  (?,?,?) ";
        try (PreparedStatement pst = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, pregunta.getEnunciado());
            pst.setString(2, pregunta.getComentarios());
            pst.setString(3, pregunta.getFuentePregunta());
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                return rs.next() ? rs.getBigDecimal(1) : null;
            }
        }
    }
    
    public static void insertarCategorias(Connection conexion, BigDecimal idPregunta, List<BigDecimal> idCategorias)throws SQLException{
        String sql = "INSERT INTO pregunta_categoria (id_pregunta, id_categoria) VALUES (?,?)";
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setBigDecimal(1, idPregunta);
            for(BigDecimal idCategoria : idCategorias){
                pst.setBigDecimal(2, idCategoria);
                pst.addBatch();
            }
            pst.executeBatch();
        }
    }
    
    public static void insertarRespuestas(Connection conexion, BigDecimal idPregunta, List<Respuesta> respuestas)throws SQLException{
        String sql = "INSERT INTO respuesta (id_pregunta, respuesta, correcta, feedback) VALUES (?,?,?,?)";
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setBigDecimal(1, idPregunta);
            for(Respuesta respuesta : respuestas){
                pst.setString(2, respuesta.getRespuesta());
                pst.setBoolean(3, respuesta.isCorrecta());
                pst.setString(4, respuesta.getFeedback());
                pst.addBatch();
            }
            pst.executeBatch();
        }
    }
}
