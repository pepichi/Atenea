/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.importacion;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class MetodoImportacion {
    
        private enum Columnas {
        DESCRIPCION, ID_METODO_IMPORTACION, NOMBRE, ORDEN, URL
    }

    private BigDecimal idMetodoImportacion;
    private String nombre;
    private String descripcion;
    private int orden;
    private String url;

    public MetodoImportacion(ResultSet rs) throws SQLException {
        idMetodoImportacion = rs.getBigDecimal(Columnas.ID_METODO_IMPORTACION.name());
        nombre = rs.getString(Columnas.NOMBRE.name());
        descripcion = rs.getString(Columnas.DESCRIPCION.name());
        orden = rs.getInt(Columnas.ORDEN.name());
        url = rs.getString(Columnas.URL.name());
    }

    public BigDecimal getIdMetodoImportacion() {
        return idMetodoImportacion;
    }

    public void setIdMetodoImportacion(BigDecimal idMetodoImportacion) {
        this.idMetodoImportacion = idMetodoImportacion;
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

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
