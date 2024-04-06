package es.tfg.atenea.core.procedimiento;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Procedimiento {

    private enum Columnas {
        DESCRIPCION, ID_PROCEDIMIENTO, NOMBRE, ORDEN, URL
    }

    private BigDecimal idProcedimiento;
    private String nombre;
    private String descripcion;
    private int orden;
    private String url;

    public Procedimiento(ResultSet rs) throws SQLException {
        idProcedimiento = rs.getBigDecimal(Columnas.ID_PROCEDIMIENTO.name());
        nombre = rs.getString(Columnas.NOMBRE.name());
        descripcion = rs.getString(Columnas.DESCRIPCION.name());
        orden = rs.getInt(Columnas.ORDEN.name());
        url = rs.getString(Columnas.URL.name());
    }

    public BigDecimal getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(BigDecimal idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
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
