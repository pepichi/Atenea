/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.jobs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author José Puerta Cardelles
 */
public class RecordatorioHelper {
    
    private RecordatorioHelper(){
        
    }
    
    public static int getDiasSinSimulacros(Connection conexion)throws SQLException{
        String sql = "  SELECT getDiasSinRealizarSimulacros() ";
        try(Statement stm = conexion.createStatement(); ResultSet rs = stm.executeQuery(sql)){
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
    
}
