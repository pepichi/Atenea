/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.configuracion.examen;

import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.ServletBase;
import es.tfg.atenea.core.helper.ServletHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author José Puerta Cardelles
 */
@WebServlet(name = "ConfiguracionExamenServlet", urlPatterns = {"/Servlet/ConfiguracionExamenServlet"})
public class ConfiguracionExamenServlet extends ServletBase{
    private static final String ERROR = "Ha ocurrido un error recuperando las configuraciones de examen disponibles";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            ServletHelper.responseObject(
                    ConfiguracionExamenHelper.getConfiguracionesDisponibles(conexion),
                    response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(ERROR, ERROR, ex, response);
        }
    }   
}
