/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.configuracion.examen;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
public class ConfiguracionExamenHelper {

    private ConfiguracionExamenHelper() {

    }

    public static boolean existeNombreConfiguracion(Connection conexion, String nombreConfiguracion) throws SQLException {
        String sql = "SELECT * FROM configuracion_examen WHERE nombre_configuracion = ? ";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, nombreConfiguracion);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }

    public static BigDecimal guardarConfiguracionExamen(Connection conexion, ConfiguracionExamen configuracion) throws SQLException {
        String sql = "INSERT INTO configuracion_examen (nombre_configuracion, numero_preguntas, respuesta_pregunta, multirespuesta, limite_tiempo, "
                + "mostrar_tiempo, mostrar_feedback, mostrar_inmediatamente, usar_todas_categorias, categorias_seleccionadas_mismo_peso) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?) ";
        try (PreparedStatement pst = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, configuracion.getNombreConfiguracion());
            pst.setInt(2, configuracion.getNumeroPreguntas());
            pst.setInt(3, configuracion.getRespuestasXPregunta());
            pst.setBoolean(4, configuracion.isMultirespuesta());
            pst.setInt(5, configuracion.getTiempo());
            pst.setBoolean(6, configuracion.isMostrarTiempo());
            pst.setBoolean(7, configuracion.isMostrarFeedback());
            pst.setBoolean(8, configuracion.isMostrarInmediatamente());
            pst.setBoolean(9, configuracion.isUsarTodasCategorias());
            pst.setBoolean(10, configuracion.isCategoriasSeleccionadasMismoPeso());
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                return rs.next() ? rs.getBigDecimal(1) : null;
            }
        }
    }

    public static void guardarCategoriasConfiguracionExamen(Connection conexion, BigDecimal idConfiguracion, List<ConfiguracionExamenCategoria> categorias) throws SQLException {
        String sql = "INSERT INTO configuracion_examen_categorias (id_configuracion_examen, id_categoria, peso) VALUES (?,?,?) ";
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setBigDecimal(1, idConfiguracion);
            for(ConfiguracionExamenCategoria categoria : categorias){
                pst.setBigDecimal(2, categoria.getIdCategoria());
                pst.setDouble(3, categoria.getPeso());
                pst.addBatch();
            }
            pst.executeBatch();
        }
    }
}
