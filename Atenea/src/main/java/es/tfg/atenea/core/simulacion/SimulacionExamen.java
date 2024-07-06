/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import es.tfg.atenea.core.helper.ORMHelper;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

/**
 *
 * @author José Puerta Cardelles
 */
public class SimulacionExamen {
    
    private enum Columnas{
        ID_SIMULACION_EXAMEN, ID_CONFIGURACION_EXAMEN, TIEMPO_UTILIZADO, FECHA
        
    }
    
    private BigDecimal idSimulacionExamen;
    private BigDecimal idConfiguracionExamen;
    private Double tiempoUtilizado;
    private GregorianCalendar fecha;
    
    public SimulacionExamen(ResultSet rs)throws SQLException{
        idSimulacionExamen = rs.getBigDecimal(Columnas.ID_SIMULACION_EXAMEN.name());
        idConfiguracionExamen = rs.getBigDecimal(Columnas.ID_CONFIGURACION_EXAMEN.name());
        tiempoUtilizado = rs.getDouble(Columnas.TIEMPO_UTILIZADO.name());
        fecha = ORMHelper.getGregorianCalendar(rs,Columnas.FECHA.name());
    }

    public BigDecimal getIdSimulacionExamen() {
        return idSimulacionExamen;
    }

    public void setIdSimulacionExamen(BigDecimal idSimulacionExamen) {
        this.idSimulacionExamen = idSimulacionExamen;
    }

    public BigDecimal getIdConfiguracionExamen() {
        return idConfiguracionExamen;
    }

    public void setIdConfiguracionExamen(BigDecimal idConfiguracionExamen) {
        this.idConfiguracionExamen = idConfiguracionExamen;
    }

    public Double getTiempoUtilizado() {
        return tiempoUtilizado;
    }

    public void setTiempoUtilizado(Double tiempoUtilizado) {
        this.tiempoUtilizado = tiempoUtilizado;
    }

    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }
}