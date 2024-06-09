package io.github.gaosups.qqi.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PlayerDTO {

	private UUID uuid;
	private String username;
	private LocalDateTime createDate;
}
