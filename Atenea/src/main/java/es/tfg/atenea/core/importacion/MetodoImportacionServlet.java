/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.importacion;

import es.tfg.atenea.core.constants.Errores;
import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.LogHelper;
import es.tfg.atenea.core.helper.ServletHelper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 *
 * @author José Puerta Cardelles
 */

@WebServlet(name = "MetodoImportacionServlet", urlPatterns = {"/Servlet/MetodoImportacionServlet"})
public class MetodoImportacionServlet extends HttpServlet{

    private static final String ERROR_CARGA_METODOS_IMPORTACION = "No se ha podido obtener los métodos de importación del sistema";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            ServletHelper.responseObject(MetodosImportacionHelper.getMetodosImportacion(conexion), response);
        } catch (Exception ex) {
            LogHelper.anotarExcepcionLog(ERROR_CARGA_METODOS_IMPORTACION, ex);
            ServletHelper.responseObjectSafe(Errores.ERROR_GENERICO.getMensajeUsuario(), response);
        }
    }
}