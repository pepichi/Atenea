/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.banco;

import es.tfg.atenea.core.categoria.CategoriaHelper;
import es.tfg.atenea.core.constants.Parametros;
import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.ServletBase;
import es.tfg.atenea.core.helper.ServletHelper;
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
@WebServlet(name = "ListaCategoriasPreguntaServlet", urlPatterns = {"/Servlet/ListaCategoriasPreguntaServlet"})
public class ListaCategoriasPreguntaServlet extends ServletBase{
    
        private static final String ERROR_MENSAJE_CARGANDO_CATEGORIAS_PREGUNTA = "Ha ocurrido un error recuperando las categorías para la pregunta: %s";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BigDecimal idPregunta = new BigDecimal(ServletHelper.getRequestObject(request, Integer.class, Parametros.ID_PREGUNTA));
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            ServletHelper.responseObject(CategoriaHelper.getCategoriasPregunta(conexion, idPregunta), response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(ERROR_MENSAJE_CARGANDO_CATEGORIAS_PREGUNTA, ERROR_MENSAJE_CARGANDO_CATEGORIAS_PREGUNTA, ex, response);
        }
    }
    
}
