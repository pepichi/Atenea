/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.configuracion.examen;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class ConfiguracionExamenCategoria {

    private enum Columnas {
        ID_CONFIGURACION_EXAMEN_CATEGORIAS, ID_CONFIGURACION_EXAMEN, ID_CATEGORIA, PESO
    }

    private BigDecimal idConfiguracionExamenCategoria;
    private BigDecimal idConfiguracionExamen;
    private BigDecimal idCategoria;
    private Double peso;

    public ConfiguracionExamenCategoria(ResultSet rs) throws SQLException {
        idConfiguracionExamenCategoria = rs.getBigDecimal(Columnas.ID_CONFIGURACION_EXAMEN_CATEGORIAS.name());
        idConfiguracionExamen = rs.getBigDecimal(Columnas.ID_CONFIGURACION_EXAMEN.name());
        idCategoria = rs.getBigDecimal(Columnas.ID_CATEGORIA.name());
        peso = rs.getDouble(Columnas.PESO.name());
    }

    public BigDecimal getIdConfiguracionExamenCategoria() {
        return idConfiguracionExamenCategoria;
    }

    public void setIdConfiguracionExamenCategoria(BigDecimal idConfiguracionExamenCategoria) {
        this.idConfiguracionExamenCategoria = idConfiguracionExamenCategoria;
    }

    public BigDecimal getIdConfiguracionExamen() {
        return idConfiguracionExamen;
    }

    public void setIdConfiguracionExamen(BigDecimal idConfiguracionExamen) {
        this.idConfiguracionExamen = idConfiguracionExamen;
    }

    public BigDecimal getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(BigDecimal idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

}
