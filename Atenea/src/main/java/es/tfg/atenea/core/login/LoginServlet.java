package es.tfg.atenea.core.login;

import es.tfg.atenea.core.constants.Atributos;
import es.tfg.atenea.core.constants.Parametros;
import es.tfg.atenea.core.constants.Redirecciones;
import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.helper.LogHelper;
import es.tfg.atenea.core.helper.ServletBase;
import es.tfg.atenea.core.objetcs.Interno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

@WebServlet(name = "LoginServlet", urlPatterns = {"/Servlet/LoginServlet"})
public class LoginServlet extends ServletBase {

    private static final String ERROR_MENSAJE_AUTENTICACION = "El usuario o la contraseña son incorrectos para usuario:%s, contraseña:%s";
    private static final String MENSAJE_AUTENTICACION_CORRECTA = "El usuario: %s, se ha autenticado correctamente";
    private static final String MENSAJE_AVISO_AUTENTICACION = "El usuario: %s, NO se ha autenticado correctamente";
    private static final String CODIGO_ERROR_NO_AUTORIZADO = "01";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter(Parametros.NOMBRE_USUARIO);
        String password = request.getParameter(Parametros.PASSWORD);
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            BigDecimal idUsuario = UsuarioHelper.getUsuarioAutenticado(conexion, nombreUsuario, password);
            if (idUsuario != null) {
                LogHelper.anotarInfoLog(String.format(MENSAJE_AUTENTICACION_CORRECTA, nombreUsuario), idUsuario);
                Interno interno = new Interno();
                interno.setUsuario(UsuarioHelper.getUsuario(conexion, idUsuario));
                request.setAttribute(Atributos.INTERNO, interno);
                response.sendRedirect(Redirecciones.FRONTAL);
                return;
            }
            LogHelper.anotarWarningLog(String.format(MENSAJE_AVISO_AUTENTICACION, nombreUsuario));
            response.sendRedirect(Redirecciones.LOGIN + "?" + Parametros.MENSAJE + "=" + CODIGO_ERROR_NO_AUTORIZADO);
        } catch (Exception ex) {
            LogHelper.anotarExcepcionLog(String.format(ERROR_MENSAJE_AUTENTICACION, nombreUsuario, password), ex);
        }

    }

}
