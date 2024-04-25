package es.tfg.atenea.core.banco;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaHelper {
    
    private CategoriaHelper(){
        
    }
    
    public static List<Categoria> getCategoriasDisponibles(Connection conexion)throws SQLException{
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try(Statement stm = conexion.prepareStatement(sql); ResultSet rs = stm.executeQuery(sql)){
            while(rs.next()){
                categorias.add(new Categoria(rs));
            }
        }
        return categorias;
    }
    
}
