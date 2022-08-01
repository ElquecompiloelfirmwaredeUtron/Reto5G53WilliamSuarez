
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    Connection connection;
    
    String driver = "com.mysql.cj.jdbc.Driver";
    String cadenaConexion = "jdbc:mysql://localhost:3306/reto1g53";
    String usuario = "root";
    String contraseña = "";  
    public Conexion() {
        try{
        Class.forName(driver);
        connection = DriverManager.getConnection(cadenaConexion, usuario, contraseña);
        
        if(connection != null){
            System.out.println("Conexión exitosa con la base de datos");
        
        }
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("No se pudo establecer conexión con la base de datos.");

        }
    
    
    }  
    public Connection getConection(){
        return connection;
    
    }

    Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


