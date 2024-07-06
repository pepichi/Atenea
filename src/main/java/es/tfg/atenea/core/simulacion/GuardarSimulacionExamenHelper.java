/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import es.tfg.atenea.core.banco.BloquePreguntaRespuestas;
import es.tfg.atenea.core.banco.Respuesta;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class GuardarSimulacionExamenHelper {

    private GuardarSimulacionExamenHelper() {

    }

    public static BigDecimal guardarSimulacionRealizada(Connection conexion, ResultadoSimulacionExamen resultado) throws SQLException {
        String sql = "  INSERT INTO simulacion_examen (id_configuracion_examen, tiempo_utilizado) VALUES (?, ?) ";
        try (PreparedStatement pst = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setBigDecimal(1, resultado.getIdConfiguracion());
            pst.setDouble(2, resultado.getTiempoUtilizado());
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                return rs.next() ? rs.getBigDecimal(1) : null;
            }
        }
    }
    
    public static void guardarPreguntasEnSimulacion(Connection conexion, BigDecimal idSimulacion, ResultadoSimulacionExamen resultado)throws SQLException{
        String sql = "  INSERT INTO simulacion_examen_detalle (id_simulacion_examen, id_respuesta, marcada) VALUES (?,?,?) ";
        boolean respuestaMarcada;
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setBigDecimal(1, idSimulacion);
            for(BloquePreguntaRespuestas bloque : resultado.getBloques()){
                for(Respuesta respuesta : bloque.getRespuestas()){
                    pst.setBigDecimal(2, respuesta.getIdRespuesta());
                    respuestaMarcada = resultado.getRespuestas().stream().anyMatch(x-> x.getIdRespuestas().contains(respuesta.getIdRespuesta()));
                    pst.setBoolean(3, respuestaMarcada);
                    pst.addBatch();
                }
            }
            pst.executeBatch();
        }
    }
    
    public static SimulacionExamen getSimulacionExamen(Connection conexion, BigDecimal idSimulacion)throws SQLException{
        String sql = "  SELECT * FROM simulacion_examen WHERE id_simulacion_examen = ? ";
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setBigDecimal(1, idSimulacion);
            try(ResultSet rs = pst.executeQuery()){
                return rs.next() ? new SimulacionExamen(rs) : null;
            }
        }
    }

}
