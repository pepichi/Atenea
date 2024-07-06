/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import es.tfg.atenea.core.categoria.Categoria;
import es.tfg.atenea.core.categoria.CategoriaHelper;
import es.tfg.atenea.core.configuracion.examen.ConfiguracionExamenHelper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author José Puerta Cardelles
 */
public class ConfiguracionSimulacionHelper {

    private ConfiguracionSimulacionHelper() {

    }

    public static ConfiguracionSimulacion getConfiguracionSimulacion(Connection conexion, BigDecimal idConfiguracion) throws SQLException {
        ConfiguracionSimulacion configuracion = new ConfiguracionSimulacion();
        configuracion.setConfiguracion(ConfiguracionExamenHelper.getConfiguracion(conexion, idConfiguracion));
        if (configuracion.getConfiguracion().isUsarTodasCategorias()) {
            List<Categoria>categorias = CategoriaHelper.getCategoriasDisponibles(conexion);
            Double peso = configuracion.getConfiguracion().getNumeroPreguntas()*1.0/categorias.size()*1.0;
            List<CategoriaExamen> categoriasExamen = categorias.stream().map(x -> new CategoriaExamen(x, peso)).collect(Collectors.toList());
            configuracion.setCategorias(categoriasExamen);
        }else{
            configuracion.setCategorias(ConfiguracionExamenHelper.getCategoriasConfiguracionExamen(conexion, idConfiguracion));
        }
        return configuracion;
    }

}
