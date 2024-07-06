/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.login;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class Usuario {
    
    private BigDecimal idUsuario;
    private String nombreUsuario;
    
    private enum Columnas{
        ID_USUARIO, NOMBRE_USUARIO
    }
    
    public Usuario(ResultSet rs)throws SQLException{
        idUsuario = rs.getBigDecimal(Columnas.ID_USUARIO.name());
        nombreUsuario = rs.getString(Columnas.NOMBRE_USUARIO.name());
    }

    public BigDecimal getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(BigDecimal idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


}
