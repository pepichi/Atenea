package es.tfg.atenea.core.login;

import es.tfg.atenea.core.constants.Atributos;
import es.tfg.atenea.core.constants.Parametros;
import es.tfg.atenea.core.constants.Redirecciones;
import es.tfg.atenea.core.database.DataBaseHelper;
import es.tfg.atenea.core.objets.Interno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/Servlet/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final String errorMensajeAutenticacion = "El usuario o la contraseña son incorrectos";
    private static final String CODIGO_ERROR_NO_AUTORIZADO = "01";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter(Parametros.NOMBRE_USUARIO);
        String password = request.getParameter(Parametros.PASSWORD);
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional()) {
            BigDecimal idUsuario = UsuarioHelper.getUsuarioAutenticado(conexion, nombreUsuario, password);
            if (idUsuario != null) {
                Interno interno = new Interno();
                interno.setUsuario(UsuarioHelper.getUsuario(conexion, idUsuario));
                request.setAttribute(Atributos.INTERNO, interno);
                response.sendRedirect(Redirecciones.FRONTAL);
                return;
            }
            response.sendRedirect(Redirecciones.LOGIN + "?" + Parametros.MENSAJE + "=" + CODIGO_ERROR_NO_AUTORIZADO);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
