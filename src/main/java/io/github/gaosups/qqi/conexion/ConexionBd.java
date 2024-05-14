package io.github.gaosups.qqi.conexion;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class ConexionBd {

	//qqi nombre de mi base de datos local cambiar
	private static final String URL = "jdbc:mysql://localhost:3306/qqsi?useSSL=false&serverTimezone" +
                                      "=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	private ConexionBd() {
		throw new IllegalStateException("Utility class");
	}

	public static @Nullable Connection obtenerConexion() {
		try {
			Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
			log.info("Conexión con la base de datos");
			return conexion;
		} catch (SQLException e) {
			log.error("Error de conexion con la base de datos" + e.getMessage(), e);
			return null;
		}
	}
}
