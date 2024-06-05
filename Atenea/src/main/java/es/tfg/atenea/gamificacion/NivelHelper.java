/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.gamificacion;

import es.tfg.atenea.core.helper.PropiedadesHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author José Puerta Cardelles
 */
public class NivelHelper {

    private static final String CLAVE_PROPIEDAD_NIVEL_ACTUAL = "gamificacion.nivel";

    private NivelHelper() {

    }

    public static int getNivelAnterior(Connection conexion) throws SQLException {
        return Integer.parseInt(PropiedadesHelper.getPropiedad(conexion, CLAVE_PROPIEDAD_NIVEL_ACTUAL));
    }

    public static int getNivelActual(Connection conexion) throws SQLException {
        String sql = " SELECT getNivelActual() ";
        try (Statement stm = conexion.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    
    public static int actualizaNivelSiCorresponde(Connection conexion)throws SQLException{
        int nivelActual = getNivelActual(conexion);
        if(nivelActual > getNivelAnterior(conexion)){
            actualizaNivel(conexion, nivelActual);
            return nivelActual;
        }
        return 0;
    }
    
    public static void actualizaNivel(Connection conexion, int nivel) throws SQLException{
        PropiedadesHelper.setPropiedad(conexion, CLAVE_PROPIEDAD_NIVEL_ACTUAL, String.valueOf(nivel));
    }

}
