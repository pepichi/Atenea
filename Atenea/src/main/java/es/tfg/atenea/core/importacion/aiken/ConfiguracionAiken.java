/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.importacion.aiken;

import es.tfg.atenea.core.categoria.Categoria;
import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
public class ConfiguracionAiken {
    
    private String patron;
    private List<Categoria> categorias;

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    
    
}
