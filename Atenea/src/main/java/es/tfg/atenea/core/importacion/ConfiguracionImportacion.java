/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.importacion;

import es.tfg.atenea.core.categoria.Categoria;
import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
public class ConfiguracionImportacion {
    
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
