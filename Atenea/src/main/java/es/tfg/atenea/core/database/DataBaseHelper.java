/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

/**
 *
 * @author José Puerta Cardelles
 */
public class DataBaseHelper {
    
    private DataBaseHelper(){
        
    }
    
    private static DataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();

        // Cargar configuración desde el archivo de propiedades
        try (InputStream input = DataBaseHelper.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties properties = new Properties();
            properties.load(input);

            config.setJdbcUrl(System.getenv("DB_JDBC_URL"));
            config.setUsername(System.getenv("DB_USERNAME"));
            config.setPassword(System.getenv("DB_PASSWORD"));
            config.setDriverClassName(properties.getProperty("driverClassName"));

            // Configuración específica de HikariCP
            config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("maximumPoolSize")));
            config.setMinimumIdle(Integer.parseInt(properties.getProperty("minimumIdle")));
            config.setIdleTimeout(Long.parseLong(properties.getProperty("idleTimeout")));

            dataSource = new HikariDataSource(config);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
    
    public static Connection getConexionNoTransacional()throws SQLException{
        return dataSource.getConnection();
    }
    
    public static Connection getConexionTransacional()throws SQLException{
        Connection conexion = getConexionNoTransacional() ;
        conexion.setAutoCommit(false);
        return conexion;
    }
}
