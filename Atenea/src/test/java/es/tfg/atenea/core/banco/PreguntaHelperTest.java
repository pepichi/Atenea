/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.banco;

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
public class PreguntaHelperTest {
    
    public PreguntaHelperTest() {
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


    @Test
    public void testGetPreguntas() throws Exception {
        System.out.println("getPreguntas");
        try(Connection conexion = DataBaseHelper.getConexionNoTransacional()){
            List<Pregunta> result = PreguntaHelper.getPreguntas(conexion);  
            assertFalse(result.isEmpty());
        }
    }
    
}
