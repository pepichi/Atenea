package es.tfg.atenea.core.procedimiento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProcedimientoHelper {

    private ProcedimientoHelper() {

    }

    public static List<Procedimiento> getProcedimientos(Connection conexion) throws SQLException {
        List<Procedimiento> procedimientos = new ArrayList<>();
        String sql = " SELECT * FROM procedimiento ORDER BY orden ASC ";
        try (Statement stm = conexion.createStatement(); ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                procedimientos.add(new Procedimiento(rs));
            }
        }
        return procedimientos;
    }
}
