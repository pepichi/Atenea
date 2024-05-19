/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.importacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
public class MetodosImportacionHelper {

    private MetodosImportacionHelper() {

    }

    public static List<MetodoImportacion> getMetodosImportacion(Connection conexion) throws SQLException {
        List<MetodoImportacion> metodosImportacion = new ArrayList<>();
        String sql = " SELECT * FROM metodo_importacion ORDER BY orden ASC ";
        try (Statement stm = conexion.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                metodosImportacion.add(new MetodoImportacion(rs));
            }
        }
        return metodosImportacion;
    }

}
