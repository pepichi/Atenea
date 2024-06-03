/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import es.tfg.atenea.core.configuracion.examen.ConfiguracionExamen;
import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
public class ConfiguracionSimulacion {
    
    private ConfiguracionExamen configuracion;
    private List<CategoriaExamen> categorias;

    public ConfiguracionExamen getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(ConfiguracionExamen configuracion) {
        this.configuracion = configuracion;
    }

    public List<CategoriaExamen> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaExamen> categorias) {
        this.categorias = categorias;
    }
    
}
