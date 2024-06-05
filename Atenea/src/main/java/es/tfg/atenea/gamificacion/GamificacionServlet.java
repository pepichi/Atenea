/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.gamificacion;

import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.ServletHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author José Puerta Cardelles
 */
@WebServlet(name = "GamificacionServlet", urlPatterns = {"/Servlet/GamificacionServlet"})
public class GamificacionServlet extends HttpServlet{
    
    private static final String ERROR_CARGANDO_DATOS_GAMIFICACION = "Ha ocurrido un error cargando los datos de gamificación asociada al usuario";
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            ServletHelper.responseObject(new Gamificacion(NivelHelper.getNivelActual(conexion), TrofeoHelper.getTrofeoAnterior(conexion)), response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(ERROR_CARGANDO_DATOS_GAMIFICACION, ERROR_CARGANDO_DATOS_GAMIFICACION, ex, response);
        }
    }
}
