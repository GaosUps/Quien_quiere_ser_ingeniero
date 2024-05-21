package io.github.gaosups.qqi.service;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.dto.PlayerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service interface for managing players.
 *
 * <p>This interface provides methods for performing CRUD operations on players.</p>
 *
 * @since 0.1.0-alpha-1
 * @see Player
 * @see PlayerDTO
 * @see UUID
 *
 * @version 0.1.0-alpha-1
 * @author ImEcuadorian
 */
public interface PlayerService {

	/**
	 * Retrieves all players and maps them to DTOs.
	 *
	 * @return a list of {@link PlayerDTO} representing all players
	 */
	List<PlayerDTO> findAll();

	/**
	 * Retrieves a player by their unique identifier and maps them to a DTO.
	 *
	 * @param id the unique identifier of the player
	 * @return an {@link Optional} containing the {@link PlayerDTO} if found, or empty if not found
	 */
	Optional<PlayerDTO> findById(UUID id);

	/**
	 * Saves a new player to the repository.
	 *
	 * @param player the player entity to be saved
	 * @return the saved {@link Player} entity
	 */
	Player save(Player player);

	/**
	 * Updates an existing player's information by their unique identifier.
	 *
	 * @param id the unique identifier of the player
	 * @param player the player entity with updated information
	 */
	void updateUserById(UUID id, Player player);

	/**
	 * Deletes a player by their unique identifier.
	 *
	 * @param id the unique identifier of the player to be deleted
	 */
	void deleteById(UUID id);

	/**
	 * Deletes a player.
	 *
	 * @param player the player entity to be deleted
	 */
	void delete(Player player);
}