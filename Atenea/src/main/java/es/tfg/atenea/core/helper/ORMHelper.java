/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

/**
 *
 * @author José Puerta Cardelles
 */
public class ORMHelper {
    
    private ORMHelper(){
        
    }
    
    public static GregorianCalendar getGregorianCalendar(ResultSet rs, String nombreColumna)throws SQLException{
        GregorianCalendar calendar = rs.getTimestamp(nombreColumna) == null ? null : new GregorianCalendar();
        if(calendar != null){
            calendar.setTimeInMillis(rs.getTimestamp(nombreColumna).getTime());
        }
        return calendar;
    }
    
    
}
