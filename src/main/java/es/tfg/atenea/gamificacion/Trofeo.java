/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.gamificacion;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class Trofeo {

    private enum Columnas {
        ID_TROFEO, NOMBRE, DESCRIPCION, PORCENTAJE, 
    }
    
    private BigDecimal idTrofeo;
    private String nombre;
    private String descripcion;
    private Double porcentaje;
    
    public Trofeo(ResultSet rs)throws SQLException{
        this.idTrofeo = rs.getBigDecimal(Columnas.ID_TROFEO.name());
        this.nombre = rs.getString(Columnas.NOMBRE.name());
        this.descripcion = rs.getString(Columnas.DESCRIPCION.name());
        this.porcentaje = rs.getDouble(Columnas.PORCENTAJE.name());
    }

    public BigDecimal getIdTrofeo() {
        return idTrofeo;
    }

    public void setIdTrofeo(BigDecimal idTrofeo) {
        this.idTrofeo = idTrofeo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
