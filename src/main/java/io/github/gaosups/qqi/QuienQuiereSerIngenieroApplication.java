package io.github.gaosups.qqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * The main entry point for the QuienQuiereSerIngeniero application.
 *
 * <p>This is a Spring Boot application that starts up the application context and initializes all beans.</p>
 *
 * <p>The application is configured using the {@link SpringBootApplication} annotation, which enables component scanning, auto-configuration, and property support.</p>
 *
 * @since 1.0
 * @version 1.0
 *
 */
@SpringBootApplication
public class QuienQuiereSerIngenieroApplication {


	/**
	 * The main method that serves as the entry point of the Spring Boot application.
	 *
	 * <p>This method delegates to Spring Boot's {@link SpringApplication#run(Class, String...)} method to launch the application.</p>
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(QuienQuiereSerIngenieroApplication.class, args);
	}
}
