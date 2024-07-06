/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.categoria;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
public class CategoriaHelper {

    private CategoriaHelper() {

    }

    public static List<Categoria> getCategoriasDisponibles(Connection conexion) throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Statement stm = conexion.prepareStatement(sql); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                categorias.add(new Categoria(rs));
            }
        }
        return categorias;
    }

    public static Categoria getCategoria(Connection conexion, BigDecimal idCategoria) throws SQLException {
        String sql = "  SELECT * FROM categoria WHERE id_categoria = ? ";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setBigDecimal(1, idCategoria);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next() ? new Categoria(rs) : null;
            }
        }
    }

    public static Categoria insertaOActualizaCategoria(Connection conexion, Categoria categoria) throws SQLException {
        if (categoria.getIdCategoria() != null) {
            actualizaCategoria(conexion, categoria);
            return categoria;
        } else {
            return getCategoria(conexion, insertarCategoria(conexion, categoria));

        }
    }

    public static void borrarCategorias(Connection conexion, List<Categoria> categorias) throws SQLException {
        String sql = "  DELETE FROM categoria WHERE id_categoria = ? ";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            for (Categoria categoria : categorias) {
                pst.setBigDecimal(1, categoria.getIdCategoria());
                pst.addBatch();
            }
            pst.executeBatch();
        }
    }

    public static void actualizaCategoria(Connection conexion, Categoria categoria) throws SQLException {
        String sql = " UPDATE categoria SET nombre = ?, descripcion = ? WHERE id_categoria = ? ";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, categoria.getNombre());
            pst.setString(2, categoria.getDescripcion());
            pst.setBigDecimal(3, categoria.getIdCategoria());
            pst.executeUpdate();
        }
    }

    public static BigDecimal insertarCategoria(Connection conexion, Categoria categoria) throws SQLException {
        String sql = "  INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement pst = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, categoria.getNombre());
            pst.setString(2, categoria.getDescripcion());
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                return rs.next() ? rs.getBigDecimal(1) : null;
            }
        }
    }

    public static List<Categoria> getCategoriasPregunta(Connection conexion, BigDecimal idPregunta) 
            throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "  SELECT      cat.* "
                + "     FROM        pregunta_categoria pc "
                + "     INNER JOIN  categoria cat "
                + "     ON          cat.id_categoria = pc.id_categoria "
                + "     WHERE       pc.id_pregunta = ? ";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setBigDecimal(1, idPregunta);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    categorias.add(new Categoria(rs));
                }
            }
        }
        return categorias;
    }

}
