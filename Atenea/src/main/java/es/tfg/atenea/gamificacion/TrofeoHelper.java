/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.gamificacion;

import es.tfg.atenea.core.helper.PropiedadesHelper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class TrofeoHelper {

    private static final String CLAVE_PROPIEDAD_TROFEO_ACTUAL = "gamificacion.trofeo";

    private TrofeoHelper() {

    }

    public static Trofeo getTrofeoAnterior(Connection conexion) throws SQLException {
        BigDecimal idTrofeoActual = new BigDecimal(PropiedadesHelper.getPropiedad(conexion, CLAVE_PROPIEDAD_TROFEO_ACTUAL));
        if (BigDecimal.ZERO.equals(idTrofeoActual)) {
            return null;
        }
        return getTrofeo(conexion, idTrofeoActual);
    }

    public static BigDecimal getIdTrofeoActual(Connection conexion, BigDecimal idSimulacion) throws SQLException {
        String sql = "  SELECT id_trofeo FROM trofeo WHERE porcentaje <= getPorcentajeAciertos(?) ORDER BY porcentaje DESC ";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setInt(1, idSimulacion.intValueExact());
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? rs.getBigDecimal(1) : null;
            }
        }
    }

    public static BigDecimal actualizaTrofeoSiCorresponde(Connection conexion, BigDecimal idSimulacion) throws SQLException {
        BigDecimal trofeoActual = getIdTrofeoActual(conexion, idSimulacion);
        Trofeo trofeoAnterior = getTrofeoAnterior(conexion);
        if(trofeoActual == null){
            return null;
        }
        if (trofeoAnterior == null || trofeoActual.compareTo(trofeoAnterior.getIdTrofeo()) > 0) {
            actualizaTrofeo(conexion, trofeoActual);
            return trofeoActual;
        }
        return null;
    }

    private static void actualizaTrofeo(Connection conexion, BigDecimal trofeoId) throws SQLException {
        PropiedadesHelper.setPropiedad(conexion, CLAVE_PROPIEDAD_TROFEO_ACTUAL, String.valueOf(trofeoId));
    }

    public static Trofeo getTrofeo(Connection conexion, BigDecimal idTrofeo) throws SQLException {
        String sql = "  SELECT * FROM trofeo WHERE id_trofeo = ? ";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setBigDecimal(1, idTrofeo);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? new Trofeo(rs) : null;
            }
        }
    }

}
