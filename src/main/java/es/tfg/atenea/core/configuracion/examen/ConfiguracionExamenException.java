/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.configuracion.examen;

/**
 *
 * @author José Puerta Cardelles
 */
public class ConfiguracionExamenException extends Exception {

    public ConfiguracionExamenException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ConfiguracionExamenException(String mensaje) {
        super(mensaje);
    }

    public ConfiguracionExamenException(Throwable causa) {
        super(causa);
    }

}