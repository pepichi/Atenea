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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author José Puerta Cardelles
 */
public class PreguntaHelper {
    
    private PreguntaHelper(){
        
    }
    
    public static List<Pregunta> getPreguntas(Connection conexion)throws SQLException{
        List<Pregunta> preguntas = new ArrayList<>();
        String sql = "SELECT * FROM pregunta WHERE activada = true order by enunciado ASC ";
        try(Statement stm = conexion.createStatement(); 
                ResultSet rs = stm.executeQuery(sql)){
            while(rs.next()){
                preguntas.add(new Pregunta(rs));
            }
        }
        return preguntas;
    }
    
    public static List<Pregunta> getPreguntasCategorias(Connection conexion, List<Categoria> categorias)throws SQLException{
        List<Pregunta> preguntas = new ArrayList<>();
        String sql = "  SELECT      * "
                + "     FROM        pregunta p "
                + "     INNER JOIN  pregunta_categoria pc "
                + "     ON          pc.id_pregunta = p.id_pregunta "
                + "     WHERE       pc.id_categorias IN (%s)";
        sql = String.format(sql, categorias.stream().map(x -> "?").collect(Collectors.joining(",")));
        int indice=1;
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            for(Categoria categoria : categorias){
                pst.setBigDecimal(indice++, categoria.getIdCategoria());
            }
            try(ResultSet rs = pst.executeQuery()){
                while(rs.next()){
                    preguntas.add(new Pregunta(rs));
                }
            }
        }
        return preguntas;        
    }
    
    public static void desactivarPregunta(Connection conexion, BigDecimal idPregunta)throws SQLException{
        String sql = "  UPDATE pregunta SET activada = false WHERE id_pregunta = ? ";
        try(PreparedStatement pst = conexion.prepareStatement(sql)){
            pst.setBigDecimal(1, idPregunta);
            pst.executeUpdate();
        }
    }
}
