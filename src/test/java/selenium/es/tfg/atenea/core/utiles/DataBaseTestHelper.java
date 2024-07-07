/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package selenium.es.tfg.atenea.core.utiles;

import es.tfg.atenea.core.helper.LogHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class DataBaseTestHelper {

    public static Connection getConexionNoTransacional() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            LogHelper.anotarExcepcionLog(ex);
        }
        return DriverManager.getConnection(System.getenv("DB_JDBC_URL"), System.getenv("DB_USERNAME"), System.getenv("DB_PASSWORD"));
    }

    public static Connection getConexionTransacional() throws SQLException {
        Connection conexion = getConexionNoTransacional();
        conexion.setAutoCommit(false);
        return conexion;
    }
}
