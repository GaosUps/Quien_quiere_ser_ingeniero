package io.github.gaosups.qqi.model.dto.mapper;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.dto.PlayerDTO;
import org.jetbrains.annotations.NotNull;

public class PlayerDTOMapper {
	private Player player;

	private PlayerDTOMapper() {
	}

	public static @NotNull PlayerDTOMapper createMapper() {
		return new PlayerDTOMapper();
	}

	public PlayerDTOMapper withPlayer(Player player) {
		this.player = player;
		return this;
	}

	public PlayerDTO build() {
		PlayerDTO playerDTO = new PlayerDTO();
		playerDTO.setUuid(player.getUuid());
		playerDTO.setUsername(player.getUsername());
		playerDTO.setEmail(player.getEmail());
		playerDTO.setCreateDate(player.getCreateDate());

		return playerDTO;
	}
}
