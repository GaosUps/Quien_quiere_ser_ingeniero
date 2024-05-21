package io.github.gaosups.qqi.controller;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.dto.PlayerDTO;
import io.github.gaosups.qqi.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {

	private final PlayerService playerService;

	@Operation(
		summary = "Get a list of players", description = "Retrieves a list of players.",
		tags = { "players" }, operationId = "getPlayers", method = "GET"
	)
	@ApiResponses(
		value = {
			@ApiResponse(
				responseCode = "200", description = "Retrieved players successfully",
				content = {
					@Content(
						mediaType = "application/json", schema = @Schema(
						implementation =
							PlayerDTO.class
					)
					)
				}
			),
			@ApiResponse(
				responseCode = "404", description = "Players not found",
				content = @Content
			)
		}
	)

	@GetMapping
	public ResponseEntity<Map<String, List<PlayerDTO>>> getPlayers() {

		return ResponseEntity.status(HttpStatus.OK)
			       .body(Map.of("players", playerService.findAll()));
	}

	@Operation(
		summary = "Create a new player for trivia game",
		description = "Create a new player in the system",
		tags = { "players" },
		operationId = "createPlayer",
		method = "POST"
	)

	@ApiResponses(
		value = {
			@ApiResponse(
				responseCode = "201", description = "Player created successfully",
				content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Player.class))
				}
			),
			@ApiResponse(
				responseCode = "400", description = "Invalid input",
				content = @Content
			)
		}
	)

	@PostMapping

	public ResponseEntity<Object> createPlayer(
		@Parameter(
			description = "Player object that needs to be added to the system. Cannot be null.",
			required = true
		)
		@Valid @RequestBody Player player,
		BindingResult result
	) {
		Map<String, String> errors = new HashMap<>();

		if (result.hasErrors()) {

			result.getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

			return ResponseEntity.badRequest()
				       .body(errors);
		}

		return ResponseEntity.status(CREATED)
			       .body(playerService.save(player));
	}
}
