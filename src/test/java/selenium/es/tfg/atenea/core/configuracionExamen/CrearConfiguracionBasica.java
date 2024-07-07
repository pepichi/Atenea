/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package selenium.es.tfg.atenea.core.configuracionExamen;

import es.tfg.atenea.core.configuracion.examen.ConfiguracionExamenHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.es.tfg.atenea.core.utiles.DataBaseTestHelper;

/**
 *
 * @author José Puerta Cardelles
 */
public class CrearConfiguracionBasica {

    private WebDriver driver;
    private static final String NOMBRE_CONFIGURACION_EXAMEN = "PruebaSeleniumConfiguracionBasicaExamen";

    public CrearConfiguracionBasica() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Test of getCategoriasDisponibles method, of class CategoriaHelper.
     */
    @Test
    public void testCrearConfiguracionBasica() throws Exception {
        System.out.println("test seleniumm creación de configuración de examen básica");
        driver.get("http://localhost:8080/Atenea/jsp/menu/frontal.jsp");
        WebElement entradaMenu = driver.findElement(By.xpath("/html/body/div/div[4]/div"));
        entradaMenu.click();
        WebElement botonGuardarConfiguracion = driver.findElement(By.id("idBotonGuardarConfiguracionExamen"));
        botonGuardarConfiguracion.click();
        WebElement inputNombrePlantilla = driver.findElement(By.id("nombrePlantillaId"));
        inputNombrePlantilla.sendKeys(NOMBRE_CONFIGURACION_EXAMEN);
        WebElement confirmacionGuardado = driver.findElement(By.id("confirmacionGuardadoPlantillaId"));
        confirmacionGuardado.click();

        // Espera breve para que se carguen los resultados
        try (Connection conexion = DataBaseTestHelper.getConexionNoTransacional()) {
            assertTrue(ConfiguracionExamenHelper.existeNombreConfiguracion(conexion, NOMBRE_CONFIGURACION_EXAMEN));
            borrarConfiguracionCreada(conexion, NOMBRE_CONFIGURACION_EXAMEN);
        }
    }

    private static void borrarConfiguracionCreada(Connection conexion, String nombreConfiguracion) throws SQLException {
        String sqlBorrarCategorias = "  DELETE FROM configuracion_examen_categorias WHERE id_configuracion_examen = (SELECT id_configuracion_examen FROM configuracion_examen WHERE nombre_configuracion = ? ) ";
        String sqlBorrarConfiguracion = "  DELETE FROM configuracion_examen WHERE nombre_configuracion = ? ";
        lanzarQueryBorrado(conexion, sqlBorrarCategorias, nombreConfiguracion);
        lanzarQueryBorrado(conexion, sqlBorrarConfiguracion, nombreConfiguracion);
    }

    private static void lanzarQueryBorrado(Connection conexion, String query, String nombreConfiguracion) throws SQLException {
        try (PreparedStatement pst = conexion.prepareStatement(query)) {
            pst.setString(1, nombreConfiguracion);
            pst.executeUpdate();
        }
    }
}
