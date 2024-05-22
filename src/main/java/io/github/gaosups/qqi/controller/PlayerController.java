package io.github.gaosups.qqi.controller;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.dto.PlayerDTO;
import io.github.gaosups.qqi.model.exceptions.PlayerNotFoundException;
import io.github.gaosups.qqi.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * REST controller for managing players in the trivia game.
 * <p>
 * This controller provides endpoints for creating, retrieving, updating, and deleting players.
 *
 * @version 1.0
 * @author ImEcuadorian
 * @see PlayerService
 * @see Player
 * @see PlayerDTO
 * @see PlayerNotFoundException
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/players")
public class PlayerController {

	private final PlayerService playerService;
	private final MessageSource messageSource;

	/**
	 * Retrieves a list of all players.
	 *
	 * @return a response entity containing a map with a list of all players
	 */
	@Operation(
		summary = "Get a list of players",
		description = "Retrieves a list of players.",
		tags = { "players" },
		operationId = "getPlayers",
		method = "GET"
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

	/**
	 * Retrieves a player by their unique identifier.
	 *
	 * @param id
	 * 	the unique identifier of the player to retrieve
	 *
	 * @return a response entity containing the player's details
	 */
	@Operation(
		summary = "Search for a player by id",
		description = "Search for a player by id",
		tags = { "players" },
		operationId = "getPlayerById",
		method = "GET"
	)
	@ApiResponses(
		value = {
			@ApiResponse(
				responseCode = "200", description = "Retrieved player successfully",
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
				responseCode = "404", description = "Player not found",
				content = @Content
			),
			@ApiResponse(
				responseCode = "400", description = "Invalid UUID supplied",
				content = @Content
			)
		}
	)
	@GetMapping("/{id}")
	public ResponseEntity<PlayerDTO> getPlayerById(
		@Parameter(
			description = "ID of the player to be obtained. Cannot be empty.",
			required = true
		) @PathVariable UUID id
	) {
		PlayerDTO playerDTO =
			playerService.findById(id)
				.orElseThrow(
					() -> new PlayerNotFoundException(
						messageSource.getMessage(
							"NotFound.player.message",
							new String[] { id.toString() },
							Locale.US)
					));

		return ResponseEntity.status(HttpStatus.OK)
			       .body(playerDTO);
	}

	/**
	 * Creates a new player.
	 *
	 * @param player
	 * 	the player object to be created
	 * @param result
	 * 	binding result to handle validation errors
	 *
	 * @return a response entity containing the created player or validation errors
	 */
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

	/**
	 * Updates a player by their unique identifier.
	 *
	 * @param id
	 * 	the unique identifier of the player to update
	 * @param player
	 * 	the player object with updated information
	 * @param result
	 * 	binding result to handle validation errors
	 *
	 * @return a response entity indicating the status of the update operation
	 */
	@Operation(
		summary = "Update a player in the system by their unique identifier",
		description = "Updates a player in the system by their unique identifier.",
		tags = { "players" }, operationId = "updatePlayer", method = "PUT"
	)
	@ApiResponses(
		value = {
			@ApiResponse(
				responseCode = "204", description = "Player updated successfully",
				content = @Content
			),
			@ApiResponse(
				responseCode = "400", description = "Invalid input",
				content = @Content
			)
		}
	)
	@PutMapping("/{id}")
	public ResponseEntity<Object> updatePlayerById(
		@Parameter(description = "ID of the player to be updated. Cannot be empty.", required = true)
		@PathVariable UUID id,
		@Valid @RequestBody Player player,
		@NotNull BindingResult result
	) {
		Map<String, String> errors = new HashMap<>();

		if (result.hasErrors()) {
			result.getFieldErrors()
				.forEach(
					error -> errors.put(error.getField(), error.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				       .body(errors);
		}

		playerService.updateUserById(id, player);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
			       .build();
	}

	/**
	 * Deletes a player by their unique identifier.
	 *
	 * @param id
	 * 	the unique identifier of the player to delete
	 *
	 * @return a response entity indicating the status of the delete operation
	 */
	@Operation(
		summary = "Delete a player from the system by their unique identifier",
		description = "Deletes a player from the system by their unique identifier.",
		tags = { "players" }, operationId = "deletePlayer", method = "DELETE"
	)
	@ApiResponses(
		value = {
			@ApiResponse(
				responseCode = "204", description = "Player deleted successfully",
				content = @Content
			),
			@ApiResponse(
				responseCode = "404", description = "Player not found",
				content = @Content
			)
		}
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePlayerById(
		@Parameter(description = "ID of the player to be deleted. Cannot be empty.", required = true)
		@PathVariable UUID id
	) {
		playerService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
			       .build();
	}
}
