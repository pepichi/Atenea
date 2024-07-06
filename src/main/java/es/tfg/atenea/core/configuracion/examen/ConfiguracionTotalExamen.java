/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.configuracion.examen;

import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
public class ConfiguracionTotalExamen {
    
    private ConfiguracionExamen configuracionExamen;
    private List<ConfiguracionExamenCategoria> categoriasExamen;

    public ConfiguracionExamen getConfiguracionExamen() {
        return configuracionExamen;
    }

    public void setConfiguracionExamen(ConfiguracionExamen configuracionExamen) {
        this.configuracionExamen = configuracionExamen;
    }

    public List<ConfiguracionExamenCategoria> getCategoriasExamen() {
        return categoriasExamen;
    }

    public void setCategoriasExamen(List<ConfiguracionExamenCategoria> categoriasExamen) {
        this.categoriasExamen = categoriasExamen;
    }
    
    
    
}
