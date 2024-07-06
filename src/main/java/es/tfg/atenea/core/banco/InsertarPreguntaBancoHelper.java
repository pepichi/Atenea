/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.banco;

import es.tfg.atenea.core.categoria.Categoria;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author José Puerta Cardelles
 */
public class InsertarPreguntaBancoHelper {

    private InsertarPreguntaBancoHelper() {

    }

    public static void insertarPreguntaYRespuestas(Connection conexion, Pregunta pregunta, List<BigDecimal> idCategorias, List<Respuesta> respuestas) throws SQLException {
        BigDecimal idPregunta = InsertarPreguntaBancoHelper.insertarEnunciado(conexion, pregunta);
        InsertarPreguntaBancoHelper.insertarCategorias(conexion, idPregunta, idCategorias);
        InsertarPreguntaBancoHelper.insertarRespuestas(conexion, idPregunta, respuestas);
    } 
    
    public static void insertarBloques(Connection conexion, List<BloquePreguntaRespuestas> bloques, List<Categoria> categorias)throws SQLException{
        for(BloquePreguntaRespuestas bloque : bloques){
            insertarBloque(conexion, bloque, categorias);    
        }
    }
    
    private static void insertarBloque(Connection conexion, BloquePreguntaRespuestas bloque, List<Categoria> categorias)throws SQLException{
        insertarPreguntaYRespuestas(conexion, bloque.getPregunta(), categorias.stream().map(x-> x.getIdCategoria()).collect(Collectors.toList()), bloque.getRespuestas());
    }

    private static BigDecimal insertarEnunciado(Connection conexion, Pregunta pregunta) throws SQLException {
        String sql = "  INSERT INTO pregunta (enunciado, fuente_pregunta, comentarios) "
                + "     VALUES  (?,?,?) ";
        try (PreparedStatement pst = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, pregunta.getEnunciado());
            pst.setString(2, pregunta.getFuentePregunta());
            pst.setString(3, pregunta.getComentarios());
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                return rs.next() ? rs.getBigDecimal(1) : null;
            }
        }
    }

    private static void insertarCategorias(Connection conexion, BigDecimal idPregunta, List<BigDecimal> idCategorias) throws SQLException {
        String sql = "INSERT INTO pregunta_categoria (id_pregunta, id_categoria) VALUES (?,?)";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setBigDecimal(1, idPregunta);
            for (BigDecimal idCategoria : idCategorias) {
                pst.setBigDecimal(2, idCategoria);
                pst.addBatch();
            }
            pst.executeBatch();
        }
    }

    private static void insertarRespuestas(Connection conexion, BigDecimal idPregunta, List<Respuesta> respuestas) throws SQLException {
        String sql = "INSERT INTO respuesta (id_pregunta, respuesta, correcta, feedback) VALUES (?,?,?,?)";
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setBigDecimal(1, idPregunta);
            for (Respuesta respuesta : respuestas) {
                pst.setString(2, respuesta.getRespuesta());
                pst.setBoolean(3, respuesta.isCorrecta());
                pst.setString(4, respuesta.getFeedback());
                pst.addBatch();
            }
            pst.executeBatch();
        }
    }

}
