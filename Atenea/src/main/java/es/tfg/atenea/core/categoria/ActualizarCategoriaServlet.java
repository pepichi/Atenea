/*
 * Copyright 2024 José Puerta Cardelles
 *
 * Permiso otorgado bajo la Licencia MIT
 */
package es.tfg.atenea.core.categoria;

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
@WebServlet(name = "ActualizarCategoriaServlet", urlPatterns = {"/Servlet/ActualizarCategoriaServlet"})
public class ActualizarCategoriaServlet extends ServletBase {
    
    private static final String ERROR_ACTUALIZANDO_INSERTANDO_CATEGORIA = "Se ha producido un error insertando/actualizando la categoría";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Categoria categoria = ServletHelper.getRequestObject(request, Categoria.class);
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            ServletHelper.responseObject(CategoriaHelper.insertaOActualizaCategoria(conexion, categoria), response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(ERROR_ACTUALIZANDO_INSERTANDO_CATEGORIA, ERROR_ACTUALIZANDO_INSERTANDO_CATEGORIA, ex, response);
        }

    }

}
