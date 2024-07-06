/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import es.tfg.atenea.core.categoria.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author José Puerta Cardelles
 */
public class CategoriaExamen extends Categoria{
    
    private enum Columnas{
        PESO
    }
    
    public CategoriaExamen(ResultSet rs)throws SQLException{
        super(rs);
        peso = rs.getDouble(Columnas.PESO.name());
    }
    
    public CategoriaExamen(Categoria categoria, Double peso){
        super(categoria);
        this.peso = peso;
    }
    
    private Double peso;

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
