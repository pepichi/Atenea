/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import es.tfg.atenea.core.simulacion.estadisticas.EstadisticasHelper;
import es.tfg.atenea.gamificacion.NivelHelper;
import es.tfg.atenea.gamificacion.TrofeoHelper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class LogrosSimulacionHelper {
    
    private LogrosSimulacionHelper(){
        
    }
    
    public static LogrosSimulacion getLogrosObtenidos(Connection conexion, BigDecimal idSimulacion)throws SQLException{
        LogrosSimulacion logros = new LogrosSimulacion();
        SimulacionExamen simulacion = GuardarSimulacionExamenHelper.getSimulacionExamen(conexion, idSimulacion);
        logros.setNumeroSegundos(simulacion.getTiempoUtilizado());
        logros.setAciertos(EstadisticasHelper.getNumeroAciertosSimulacion(conexion, idSimulacion));
        logros.setFallos(EstadisticasHelper.getNumeroFallosSimulacion(conexion, idSimulacion));
        logros.setSinContestar(EstadisticasHelper.getNumeroRespuestasSinContestar(conexion, idSimulacion));
        BigDecimal idNuevoTrofeo = TrofeoHelper.actualizaTrofeoSiCorresponde(conexion, idSimulacion);
        if(idNuevoTrofeo != null){
            logros.setNuevoTrofeo(true);
            logros.setTrofeo(TrofeoHelper.getTrofeo(conexion, idSimulacion));
        }
        int nivel = NivelHelper.actualizaNivelSiCorresponde(conexion);
        if(nivel > 0){
            logros.setNuevoNivel(nivel);
            logros.setSubidaNivel(true);
        }
        return logros;
    }
    
    
    
    
}
