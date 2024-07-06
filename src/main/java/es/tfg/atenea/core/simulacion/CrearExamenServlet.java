/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.simulacion;

import es.tfg.atenea.core.banco.BloquePreguntaRespuestas;
import es.tfg.atenea.core.banco.Pregunta;
import es.tfg.atenea.core.constants.Parametros;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Puerta Cardelles
 */
@WebServlet(name = "CrearExamenServlet", urlPatterns = {"/Servlet/CrearExamenServlet"})
public class CrearExamenServlet extends ServletBase{
    private static final String ERROR = "Ha ocurrido un error generando la simulación de examen para la configuración: %s";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BigDecimal idConfiguracion = new BigDecimal(ServletHelper.getRequestObject(request, Integer.class, Parametros.CONFIGURACION));
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            ConfiguracionSimulacion configuracion = ConfiguracionSimulacionHelper.getConfiguracionSimulacion(conexion, idConfiguracion);
            List<Pregunta> preguntasExamen = GenerarExamenHelper.getPreguntasExamen(conexion, configuracion);
            List<BloquePreguntaRespuestas> bloques = new ArrayList<>();
            for(Pregunta pregunta: preguntasExamen){
                bloques.add(
                        new BloquePreguntaRespuestas(
                                pregunta, 
                                GenerarExamenHelper.gerRespuestaCategoria(
                                        conexion, 
                                        pregunta.getIdPregunta(), 
                                        configuracion.getConfiguracion().isMultirrespuesta(), 
                                        configuracion.getConfiguracion().getRespuestasXPregunta())));
            }
            ServletHelper.responseObject(bloques,response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(String.format(ERROR, idConfiguracion), String.format(ERROR, idConfiguracion), ex, response);
        }
    } 
}