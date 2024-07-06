/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion.estadisticas;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class EstadisticasHelper {

    private EstadisticasHelper() {

    }

    public static int getNumeroAciertosSimulacion(Connection conexion, BigDecimal idSimulacion) throws SQLException {
        return getValorEstadistica(conexion, "  SELECT getRespuestasCorrectas(?) ", idSimulacion);
    }
    
    public static int getNumeroFallosSimulacion(Connection conexion, BigDecimal idSimulacion) throws SQLException {
        return getValorEstadistica(conexion, "  SELECT getRespuestasFalladas(?) ", idSimulacion);
    }
    
    public static int getNumeroRespuestasSinContestar(Connection conexion, BigDecimal idSimulacion) throws SQLException {
        return getValorEstadistica(conexion, "  SELECT getRespuestasSinContestar(?) ", idSimulacion);
    }

    private static int getValorEstadistica(Connection conexion, String sql, BigDecimal idSimulacion) throws SQLException {
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setBigDecimal(1, idSimulacion);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

}
