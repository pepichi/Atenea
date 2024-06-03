/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import es.tfg.atenea.core.banco.Pregunta;
import es.tfg.atenea.core.banco.Respuesta;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author José Puerta Cardelles
 */
public class GenerarExamenHelper {

    private GenerarExamenHelper() {

    }

    public static List<Pregunta> getPreguntasExamen(Connection conexion, ConfiguracionSimulacion configuracionSimulacion)throws SQLException {
        List<Pregunta> preguntasGeneradas = new ArrayList<>();
        Double totalPesos = configuracionSimulacion.getCategorias().stream().mapToDouble(CategoriaExamen::getPeso).sum();
        for (CategoriaExamen categoria : configuracionSimulacion.getCategorias()) {
            int numeroPreguntasCategoria = (int) Math.ceil(categoria.getPeso() * configuracionSimulacion.getConfiguracion().getNumeroPreguntas()/totalPesos);
            preguntasGeneradas.addAll(getPreguntaCategoria(conexion, categoria.getIdCategoria(), numeroPreguntasCategoria, preguntasGeneradas));
        }
        Collections.shuffle(preguntasGeneradas);
        return preguntasGeneradas.stream().limit(configuracionSimulacion.getConfiguracion().getNumeroPreguntas()).collect(Collectors.toList());
    }

    private static List<Pregunta> getPreguntaCategoria(Connection conexion, BigDecimal idCategoria, int limite, List<Pregunta> preguntasPrevias) throws SQLException {
        List<Pregunta> preguntas = new ArrayList<>();
        String sql = "  SELECT      p.* "
                + "     FROM        pregunta p "
                + "     INNER JOIN  pregunta_categoria pc "
                + "     ON          p.id_pregunta =pc.id_pregunta "
                + "     WHERE       pc.id_categoria = ? ";
        if (!preguntasPrevias.isEmpty()) {
            sql += String.format("    AND p.id_pregunta NOT IN (%s) ", preguntasPrevias.stream().map(x -> "?").collect(Collectors.joining(",")));
        }
        sql += "        ORDER BY    RAND() "
                + "     LIMIT ? ";
        int indice = 1;
        try (PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setBigDecimal(indice++, idCategoria);
            for (Pregunta preguntaPrevia : preguntasPrevias) {
                pst.setBigDecimal(indice++, preguntaPrevia.getIdPregunta());
            }
            pst.setInt(indice++, limite);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    preguntas.add(new Pregunta(rs));
                }
            }
        }
        return preguntas;
    }
    
    public static List<Respuesta> gerRespuestaCategoria(Connection conexion, BigDecimal idPregunta, boolean esMultirespuesta, int numeroRespuestas)
            throws SQLException, GenerarExamenException{
        List<Respuesta> respuestas = new ArrayList<>();
        Respuesta respuestaCorrecta = getRespuestaCorrecta(conexion, idPregunta);
        if(respuestaCorrecta == null){
            throw new GenerarExamenException(String.format("No existen ninguna respuesta correcta para la pregunta con el identificador: %s", idPregunta));
        }
        respuestas.add(respuestaCorrecta);
        respuestas.addAll(esMultirespuesta ? 
                getOtrasRespuestas(conexion, idPregunta, respuestaCorrecta.getIdRespuesta(), numeroRespuestas -1) : 
                getRespuestasFalsas(conexion, idPregunta, numeroRespuestas));
        Collections.shuffle(respuestas);
        return respuestas;
    }
    
    private static Respuesta getRespuestaCorrecta(Connection conexion, BigDecimal idPregunta)throws SQLException{
        String sql = "  SELECT * FROM respuesta WHERE id_pregunta = ? AND correcta = true ORDER BY RAND() LIMIT 1 ";;
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setBigDecimal(1, idPregunta);
            try(ResultSet rs = pst.executeQuery()){
                return rs.next() ? new Respuesta(rs) : null;
            }
        }
    }
    
    private static List<Respuesta> getRespuestasFalsas(Connection conexion, BigDecimal idPregunta, int limiteRespuestas)
            throws SQLException{
        List<Respuesta> respuestas = new ArrayList<>();
        String sql = "  SELECT * FROM respuesta WHERE id_pregunta = ? AND correcta = false ORDER BY RAND() LIMIT ? ";
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setBigDecimal(1, idPregunta);
            pst.setInt(2, limiteRespuestas);
            try(ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    respuestas.add(new Respuesta(rs));
                }
            }
        }
        return respuestas;
    }
    
    private static List<Respuesta> getOtrasRespuestas(Connection conexion, BigDecimal idPregunta, BigDecimal idRespuestaCorrecta, int limiteRespuestas)
            throws SQLException{
        List<Respuesta> respuestas = new ArrayList<>();
        String sql = "  SELECT * FROM respuesta WHERE id_pregunta = ? AND id_respuesta != ? ORDER BY RAND() LIMIT ? ";
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setBigDecimal(1, idPregunta);
            pst.setBigDecimal(2, idRespuestaCorrecta);
            pst.setInt(3, limiteRespuestas);
            try(ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    respuestas.add(new Respuesta(rs));
                }
            }
        }
        return respuestas;        
    }

}
