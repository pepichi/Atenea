/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.configuracion.examen;

import es.tfg.atenea.core.constants.Parametros;
import es.tfg.atenea.core.constants.ResponseTypes;
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
@WebServlet(name = "ComprobarNombreUnicoConfiguracionServlet", urlPatterns = {"/Servlet/ComprobarNombreUnicoConfiguracionServlet"})
public class ComprobarNombreUnicoConfiguracionServlet extends HttpServlet {

    private static final String ERROR = "Ha ocurrido un error comprobando la existencia del nombre de configuración: %s";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreConfiguracion = ServletHelper.getRequestObject(request, String.class, Parametros.NOMBRE_CONFIGURACION);
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            ServletHelper.responseObject(
                    ConfiguracionExamenHelper.existeNombreConfiguracion(conexion, nombreConfiguracion)
                    ? ResponseTypes.EXISTE.name()
                    : ResponseTypes.CORRECTO,
                    response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(
                    String.format(ERROR, nombreConfiguracion),
                    String.format(ERROR, nombreConfiguracion),
                    ex,
                    response);
        }
    }
}
