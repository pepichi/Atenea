/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.categoria;

import es.tfg.atenea.core.helper.ORMHelper;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

/**
 *
 * @author José Puerta Cardelles
 */
public class Categoria {

    private enum Columnas {
        DESCRIPCION, FECHA_INSERCION, ID_CATEGORIA, NOMBRE
    }

    private BigDecimal idCategoria;
    private String nombre;
    private String descripcion;
    private GregorianCalendar fechaInsercion;

    public Categoria(ResultSet rs) throws SQLException {
        idCategoria = rs.getBigDecimal(Columnas.ID_CATEGORIA.name());
        nombre = rs.getString(Columnas.NOMBRE.name());
        descripcion = rs.getString(Columnas.DESCRIPCION.name());
        fechaInsercion = ORMHelper.getGregorianCalendar(rs, Columnas.FECHA_INSERCION.name());
    }

    public Categoria(Categoria categoria) {
        idCategoria = categoria.getIdCategoria();
        nombre = categoria.getNombre();
        descripcion = categoria.getDescripcion();
        fechaInsercion = categoria.getFechaInsercion();
    }

    public BigDecimal getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(BigDecimal idCategoria) {
        this.idCategoria = idCategoria;
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

    public GregorianCalendar getFechaInsercion() {
        return fechaInsercion;
    }

    public void setFechaInsercion(GregorianCalendar fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }
}