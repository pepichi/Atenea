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

@WebServlet(name = "ListadoCategoriaServlet", urlPatterns = {"/Servlet/ListadoCategoriaServlet"})
public class ListadoCategoriaServlet extends ServletBase {
    
    private static final String ERROR_MENSAJE_CARGANDO_CATEGORIAS = "Ha ocurrido un error cargando las categorías disponibles.";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            ServletHelper.responseObject(CategoriaHelper.getCategoriasDisponibles(conexion), response);
        } catch (Exception ex) {
            ServletHelper.responderMensajeError(ERROR_MENSAJE_CARGANDO_CATEGORIAS, ERROR_MENSAJE_CARGANDO_CATEGORIAS, ex, response);
        }

    }
}
