package es.tfg.atenea.core.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

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
