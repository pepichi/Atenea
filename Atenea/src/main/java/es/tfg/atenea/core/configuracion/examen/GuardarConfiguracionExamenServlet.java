/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.configuracion.examen;

import es.tfg.atenea.core.constants.ResponseTypes;
import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.ServletHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

/**
 *
 * @author José Puerta Cardelles
 */
@WebServlet(name = "GuardarConfiguracionExamenServlet", urlPatterns = {"/Servlet/GuardarConfiguracionExamenServlet"})
public class GuardarConfiguracionExamenServlet extends HttpServlet {

    private static final String ERROR = "Este es un error";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConfiguracionTotalExamen configuracionTotal = ServletHelper.getRequestObject(request, ConfiguracionTotalExamen.class);
        try (Connection conexion = DataBaseHelper.getConexionTransacional()) {
            BigDecimal idConfiguracion = ConfiguracionExamenHelper.guardarConfiguracionExamen(conexion, configuracionTotal.getConfiguracionExamen());
            ConfiguracionExamenHelper.guardarCategoriasConfiguracionExamen(conexion, idConfiguracion, configuracionTotal.getCategoriasExamen());
            conexion.commit();
            ServletHelper.responseObject(ResponseTypes.CORRECTO, response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(ERROR, ERROR, ex, response);
        }
    }
}
