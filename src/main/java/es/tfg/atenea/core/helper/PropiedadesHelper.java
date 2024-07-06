/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.helper;

import es.tfg.atenea.core.database.DataBaseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class PropiedadesHelper {

    private PropiedadesHelper() {

    }

    public static final String getPropiedad(String propiedad) {
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            return PropiedadesHelper.getPropiedad(conexion, propiedad);
        } catch (SQLException ex) {
            LogHelper.anotarExcepcionLog(ex);
            return null;
        }
    }

    public static String getPropiedad(Connection conexion, String clave) throws SQLException {
        String sql = "  SELECT valor FROM propiedades WHERE clave = ? ";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, clave);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? rs.getString(1) : null;
            }
        }
    }

    public static void setPropiedad(Connection conexion, String clave, String valor) throws SQLException {
        String sql = "  UPDATE propiedades SET valor = ? WHERE clave = ? ";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, valor);
            pst.setString(2, clave);
            pst.executeUpdate();
        }
    }
}
