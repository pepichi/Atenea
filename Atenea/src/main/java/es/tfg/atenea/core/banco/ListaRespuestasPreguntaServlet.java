/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.banco;

import es.tfg.atenea.core.constants.Parametros;
import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.ServletHelper;
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
@WebServlet(name = "ListaRespuestasPreguntaServlet", urlPatterns = {"/Servlet/ListaRespuestasPreguntaServlet"})
public class ListaRespuestasPreguntaServlet extends HttpServlet{

    private static final String ERROR_MENSAJE_CARGANDO_RESPUESTAS_PREGUNTA = "Ha ocurrido un error recuperando las respuestas para la pregunta: %s";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BigDecimal idPregunta = new BigDecimal(ServletHelper.getRequestObject(request, Integer.class, Parametros.ID_PREGUNTA));
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            ServletHelper.responseObject(RespuestaHelper.getRespuestaPregunta(conexion, idPregunta), response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(ERROR_MENSAJE_CARGANDO_RESPUESTAS_PREGUNTA, ERROR_MENSAJE_CARGANDO_RESPUESTAS_PREGUNTA, ex, response);
        }
    }
}
