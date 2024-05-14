package com.juego.qqi;

import com.juego.qqi.conexion.ConexionBd;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuienQuiereSerIngenieroApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuienQuiereSerIngenieroApplication.class, args);
		ConexionBd.obtenerConexion();
	}

}
