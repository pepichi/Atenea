package es.tfg.atenea.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class ORMHelper {
    
    private ORMHelper(){
        
    }
    
    public static GregorianCalendar getGregorianCalendar(ResultSet rs, String nombreColumna)throws SQLException{
        GregorianCalendar calendar = rs.getDate(nombreColumna) == null ? null : new GregorianCalendar();
        if(calendar != null){
            calendar.setTimeInMillis(rs.getDate(nombreColumna).getTime());
        }
        return calendar;
    }
    
}
