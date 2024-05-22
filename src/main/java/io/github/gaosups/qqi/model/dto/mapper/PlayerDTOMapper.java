package io.github.gaosups.qqi.model.dto.mapper;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.dto.PlayerDTO;
import org.jetbrains.annotations.NotNull;

/**
 * Mapper class for converting {@link Player} entities to {@link PlayerDTO} instances.
 *
 * <p>This class provides a fluent API for mapping a Player object to a PlayerDTO object.
 * It helps in separating the mapping logic from the entity and DTO classes.</p>
 *
 * @author ImEcuadorian
 * @version 1.0
 * @see Player
 * @see PlayerDTO
 * @since 1.0
 */
public class PlayerDTOMapper {

	private Player player;

	private PlayerDTOMapper() {
	}

	/**
	 * Creates a new instance of {@link PlayerDTOMapper}.
	 *
	 * @return a new instance of PlayerDTOMapper
	 */
	public static @NotNull PlayerDTOMapper createMapper() {
		return new PlayerDTOMapper();
	}

	/**
	 * Sets the {@link Player} entity to be mapped.
	 *
	 * @param player the Player entity
	 * @return the current instance of PlayerDTOMapper
	 */
	public PlayerDTOMapper withPlayer(Player player) {
		this.player = player;
		return this;
	}

	/**
	 * Builds and returns a {@link PlayerDTO} instance with the mapped data from the {@link Player} entity.
	 *
	 * @return a PlayerDTO instance
	 */
	public PlayerDTO build() {
		PlayerDTO playerDTO = new PlayerDTO();
		playerDTO.setUuid(player.getUuid());
		playerDTO.setUsername(player.getUsername());
		playerDTO.setEmail(player.getEmail());
		playerDTO.setCreateDate(player.getCreateDate());

		return playerDTO;
	}
}
