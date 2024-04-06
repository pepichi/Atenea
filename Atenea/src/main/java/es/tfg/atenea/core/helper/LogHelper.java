package es.tfg.atenea.core.helper;

import es.tfg.atenea.core.database.DataBaseHelper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogHelper {

    private enum Nivel {
        DEBUG(0), INFO(1), WARNING(2), ERROR(3), SEVERE(4);

        private final int valor;

        Nivel(final int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }
    }

    private LogHelper() {

    }

    public static void anotarExcepcionLog(String mensaje, Exception excepcion) {
        anotarLog(mensaje, excepcion, null, null, Nivel.SEVERE);
    }

    public static void anotarInfoLog(String mensaje, BigDecimal idUsuario) {
        anotarLog(mensaje, null, idUsuario, null, Nivel.INFO);
    }

    public static void anotarWarningLog(String mensaje) {
        anotarLog(mensaje, null, null, null, Nivel.WARNING);
    }

    private static void anotarLog(String mensaje, Exception excepcion, BigDecimal idUsuario, BigDecimal idSesion, Nivel nivel) {
        String sql = "INSERT INTO LOG (mensaje,traza,id_usuario,id_sesion,nivel) VALUES (?,?,?,?,?)";
        try (Connection conexion = DataBaseHelper.getConexionNoTransacional(); PreparedStatement pst = conexion.prepareStatement(sql)) {
            pst.setString(1, getStringLog(mensaje, 4000));
            pst.setString(2, excepcion == null ? null : getStringLog(excepcion.getMessage(), 4000));
            pst.setBigDecimal(3, idUsuario);
            pst.setBigDecimal(4, idSesion);
            pst.setInt(5, nivel.getValor());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    private static String getStringLog(String mensaje, int maximo){
        if(mensaje == null){
            return null;
        }
        return mensaje.length()> maximo ? mensaje.substring(1, maximo) : mensaje;
    }

}
