package es.tfg.atenea.core.procedimiento;

import es.tfg.atenea.core.constants.Errores;
import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.LogHelper;
import es.tfg.atenea.core.helper.ServletHelper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "ProcedimientoServlet", urlPatterns = {"/Servlet/ProcedimientoServlet"})
public class ProcedimientoServlet extends HttpServlet {

    private static final String ERROR_PROCEDIMIENTOS = "No se ha podido obtener los procedimientos del sistema";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            ServletHelper.responseObject(ProcedimientoHelper.getProcedimientos(conexion), response);
        } catch (Exception ex) {
            LogHelper.anotarExcepcionLog(ERROR_PROCEDIMIENTOS, ex);
            ServletHelper.responseObjectSafe(Errores.ERROR_GENERICO.getMensajeUsuario(), response);
        }
    }
}
