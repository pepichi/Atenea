/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.tfg.atenea.core.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Pepichi
 */
public class Pruebas {
    
    private Pruebas(){
        
    }
    
   
    public static void probar2(){
                String consultaSQL = "SELECT * FROM prueba";
        try(Connection conexion = DataBaseHelper.getConexionNoTransacional();
                PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            // Ejecutar la consulta y obtener el resultado
            ResultSet resultSet = statement.executeQuery()
                ){
             while (resultSet.next()) {
                // Obtener valores de las columnas
                int columna1 = resultSet.getInt(1);
                String columna2 = resultSet.getString(2);

                // Realizar operaciones con los valores obtenidos
                System.out.println("Columna1: " + columna1 + ", Columna2: " + columna2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
}
