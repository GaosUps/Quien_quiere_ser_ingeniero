package io.github.gaosups.qqi.service.implementation;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.dto.PlayerDTO;
import io.github.gaosups.qqi.model.dto.mapper.PlayerDTOMapper;
import io.github.gaosups.qqi.model.exceptions.PlayerNotFoundException;
import io.github.gaosups.qqi.repository.PlayerRepository;
import io.github.gaosups.qqi.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Implementation of the {@link PlayerService} interface, providing methods for managing players.
 *
 * <p>This service handles CRUD operations and utilizes {@link PlayerRepository} for database
 * interactions.
 * It also uses {@link PlayerDTOMapper} for converting between entity and DTO objects.</p>
 *
 * @author ImEcuadorian
 * @version 0.1.0-alpha-1
 * @see PlayerService
 * @see PlayerRepository
 * @see PlayerDTOMapper
 * @see PlayerNotFoundException
 * @see MessageSource
 * @since 0.1.0-alpha-1
 */
@RequiredArgsConstructor
@Service
public class PlayerServiceImpl
	implements PlayerService {

	private final PlayerRepository playerRepository;
	private final MessageSource messageSource;

	/**
	 * Retrieves all players from the repository and maps them to DTOs.
	 *
	 * @return a list of {@link PlayerDTO} representing all players
	 */
	@Override
	@Transactional(readOnly = true)
	public List<PlayerDTO> findAll() {
		Stream<Player> users =
			StreamSupport.stream(playerRepository.findAll()
				                     .spliterator(), false);

		return users.map(PlayerDTOMapper.createMapper()::withPlayer)
			       .map(PlayerDTOMapper::build)
			       .toList();
	}

	/**
	 * Retrieves a player by their unique identifier and maps them to a DTO.
	 *
	 * @param id
	 * 	the unique identifier of the player
	 *
	 * @return an {@link Optional} containing the {@link PlayerDTO} if found, or empty if not found
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<PlayerDTO> findById(final UUID id) {
		return playerRepository.findById(id)
			       .map(PlayerDTOMapper.createMapper()::withPlayer)
			       .map(PlayerDTOMapper::build);
	}

	/**
	 * Saves a new player to the repository.
	 *
	 * @param player
	 * 	the player entity to be saved
	 *
	 * @return the saved {@link Player} entity
	 */
	@Override
	@Transactional
	public Player save(final Player player) {
		return playerRepository.save(player);
	}

	/**
	 * Updates an existing player's username by their unique identifier.
	 *
	 * @param id
	 * 	the unique identifier of the player
	 * @param player
	 * 	the player entity with updated information
	 *
	 * @throws PlayerNotFoundException
	 * 	if the player with the specified id is not found
	 */
	@Override
	@Transactional
	public void updateUserById(final UUID id, final Player player) {
		playerRepository.findById(id)
			.ifPresentOrElse(
				player1 -> player.setUsername(player1.getUsername()),
				() -> {
					throw new PlayerNotFoundException(
						messageSource.getMessage(
							"user.message.notfound",
							new String[] { id.toString() },
							Locale.US)
					);
				}
			);
	}

	/**
	 * Deletes a player by their unique identifier.
	 *
	 * @param id
	 * 	the unique identifier of the player to be deleted
	 */
	@Override
	@Transactional
	public void deleteById(final UUID id) {
		playerRepository.deleteById(id);
	}

	/**
	 * Deletes a player.
	 *
	 * @param player
	 * 	the player entity to be deleted
	 */
	@Override
	@Transactional
	public void delete(final Player player) {
		playerRepository.delete(player);
	}
}
