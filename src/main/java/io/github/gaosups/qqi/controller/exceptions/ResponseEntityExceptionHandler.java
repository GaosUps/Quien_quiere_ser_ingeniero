package io.github.gaosups.qqi.controller.exceptions;

import io.github.gaosups.qqi.model.exceptions.PlayerNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResponseEntityExceptionHandler {

	@ExceptionHandler(PlayerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Map<String, String>> userNotFound(@NotNull PlayerNotFoundException e) {

		Map<String, String> bodyResponse = new HashMap<>();
		bodyResponse.put("timestamp", String.valueOf(LocalDateTime.now()));
		bodyResponse.put("message", e.getMessage());
		bodyResponse.put("status", HttpStatus.NOT_FOUND.toString());

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			       .body(bodyResponse);
	}
}
