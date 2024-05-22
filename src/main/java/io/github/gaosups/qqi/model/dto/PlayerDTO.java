package io.github.gaosups.qqi.model.dto;

import io.github.gaosups.qqi.model.Player;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for {@link Player} entities.
 *
 * <p>This class is used to transfer player data between different layers of the application without
 * exposing the actual entity.</p>
 *
 * @author ImEcuadorian
 * @version 1.0
 * @see Player
 * @see UUID
 * @see LocalDateTime
 * @see Data
 * @since 1.0
 */
@Data
public class PlayerDTO {

	private UUID uuid;
	private String username;
	private String email;
	private LocalDateTime createDate;
}
