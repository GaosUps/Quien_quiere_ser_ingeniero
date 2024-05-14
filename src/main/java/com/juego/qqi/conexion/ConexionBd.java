package com.juego.qqi.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ConexionBd {
    private static final Logger logger = Logger.getLogger(ConexionBd.class.getName());
    //qqi nombre de mi base de datos local cambiar
    private static final String URL = "jdbc:mysql://localhost:3306/qqsi?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public  static Connection obtenerConexion() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.log(Level.INFO, "Conexión con la base de datos");
            return conexion;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error de conexion con la base de datos" + e.getMessage(), e);
            return null;
        }
    }
}
