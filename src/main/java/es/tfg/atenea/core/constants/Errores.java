/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.constants;

/**
 *
 * @author José Puerta Cardelles
 */
public enum Errores {
    ERROR_NO_AUTENTICADO("01", "Usuario no autenticado"),
    ERROR_GENERICO("E00", "Error: Ha ocurrido un error realizando la operación");

    private final String codigo;
    private final String descripcion;

    Errores(final String codigo, final String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public String getMensajeUsuario(){
        return String.format("(%s) %s", codigo, descripcion);
    }

}
