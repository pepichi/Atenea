/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.tfg.atenea.core.categoria;

import com.google.gson.reflect.TypeToken;
import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.ServletBase;
import es.tfg.atenea.core.helper.ServletHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Pepichi
 */


@WebServlet(name = "BorrarCategoriaServlet", urlPatterns = {"/Servlet/BorrarCategoriaServlet"})
public class BorrarCategoriaServlet  extends ServletBase {
    private static final String ERROR_BORRANDO_CATEGORIAS = "Se ha producido un error borrando las categorías";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Categoria> categorias = ServletHelper.getRequestObject(request, new TypeToken<List<Categoria>>(){}.getType());
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            CategoriaHelper.borrarCategorias(conexion, categorias);
            ServletHelper.responseObject(categorias, response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(ERROR_BORRANDO_CATEGORIAS, ERROR_BORRANDO_CATEGORIAS, ex, response);
        }
    }
}
