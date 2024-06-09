package io.github.gaosups.qqi.service;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.dto.PlayerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerService {

	List<PlayerDTO> findAll();

	Optional<PlayerDTO> findById(UUID id);

	Player save(Player player);

	void updateUserById(UUID id, Player player);

	void deleteById(UUID id);

	void delete(Player player);
}