/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.tfg.atenea.core.categoria;

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
 * @author Pepichi
 */
@WebServlet(name = "ActualizarCategoriaServlet", urlPatterns = {"/Servlet/ActualizarCategoriaServlet"})
public class ActualizarCategoriaServlet extends HttpServlet {
    
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
