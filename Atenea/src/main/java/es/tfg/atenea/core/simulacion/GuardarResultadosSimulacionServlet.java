/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.ServletBase;
import es.tfg.atenea.core.helper.ServletHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

/**
 *
 * @author José Puerta Cardelles
 */
@WebServlet(name = "GuardarResultadosSimulacionServlet", urlPatterns = {"/Servlet/GuardarResultadosSimulacionServlet"})
public class GuardarResultadosSimulacionServlet extends ServletBase {

    private static final String ERROR_GUARDANDO_EXAMEN = "Ha ocurrido un error realizando el examen";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResultadoSimulacionExamen resultado = ServletHelper.getRequestObject(request, ResultadoSimulacionExamen.class);
        try (Connection conexion = DataBaseHelper.getConexionTransacional()) {
            BigDecimal idSimulacion = GuardarSimulacionExamenHelper.guardarSimulacionRealizada(conexion, resultado);
            GuardarSimulacionExamenHelper.guardarPreguntasEnSimulacion(conexion, idSimulacion, resultado);
            LogrosSimulacion logros = LogrosSimulacionHelper.getLogrosObtenidos(conexion, idSimulacion);
            conexion.commit();
            ServletHelper.responseObject(logros, response);
            
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(ERROR_GUARDANDO_EXAMEN, ERROR_GUARDANDO_EXAMEN, ex, response);
        }
    }
}
