/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.banco;

import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.ServletHelper;
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
@WebServlet(name = "ListadoPreguntasServlet", urlPatterns = {"/Servlet/ListadoPreguntasServlet"})
public class ListadoPreguntasServlet extends HttpServlet {
    
    private static final String ERROR_MENSAJE_CARGANDO_PREGUNTAS = "Ha ocurrido un error cargando las preguntas disponibles.";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            ServletHelper.responseObject(PreguntaHelper.getPreguntas(conexion), response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(ERROR_MENSAJE_CARGANDO_PREGUNTAS, ERROR_MENSAJE_CARGANDO_PREGUNTAS, ex, response);
        }
    }
    
}
