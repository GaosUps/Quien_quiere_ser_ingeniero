package io.github.gaosups.qqi.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Exception thrown when a player is not found in the repository.
 *
 * <p>This exception is annotated with {@link ResponseStatus} to return a 404 Not Found status code
 * when thrown.
 * The {@link ResponseBody} annotation indicates that the exception message should be written
 * directly to the HTTP response body.</p>
 *
 * @version 1.0
 * @author ImEcuadorian
 * @see RuntimeException
 * @see HttpStatus
 * @see ResponseBody
 * @see ResponseStatus
 * @since 1.0
 */
@ResponseBody
@ResponseStatus(NOT_FOUND)
public class PlayerNotFoundException
	extends RuntimeException {

	public PlayerNotFoundException(final String message) {
		super(message);
	}
}
