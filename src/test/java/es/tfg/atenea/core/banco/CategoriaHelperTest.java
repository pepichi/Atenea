/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.banco;

import es.tfg.atenea.core.categoria.Categoria;
import es.tfg.atenea.core.categoria.CategoriaHelper;
import es.tfg.atenea.core.database.DataBaseHelper;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author José Puerta Cardelles
 */
public class CategoriaHelperTest {
    
    public CategoriaHelperTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getCategoriasDisponibles method, of class CategoriaHelper.
     */
    @Test
    public void testGetCategoriasDisponibles() throws Exception {
        System.out.println("getCategoriasDisponibles");
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            List<Categoria> result = CategoriaHelper.getCategoriasDisponibles(conexion);
            assertFalse(result.isEmpty());
        }
    }
    
}
