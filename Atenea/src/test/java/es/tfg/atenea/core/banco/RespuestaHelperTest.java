package es.tfg.atenea.core.banco;

import es.tfg.atenea.core.database.DataBaseHelper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RespuestaHelperTest {

    public RespuestaHelperTest() {
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
    public void testGetRespuestaPregunta() throws Exception {
        System.out.println("getRespuestaPregunta");
        BigDecimal idPregunta = null;
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            List<Respuesta> result = RespuestaHelper.getRespuestaPregunta(conexion, idPregunta);
            assertTrue(result.isEmpty());
        }
    }

}
