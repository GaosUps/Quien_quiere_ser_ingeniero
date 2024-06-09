package io.github.gaosups.qqi.model.exceptions;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseBody
@ResponseStatus(NOT_FOUND)
public class PlayerNotFoundException
	extends RuntimeException {

	public PlayerNotFoundException(final String message) {
		super(message);
	}
}
