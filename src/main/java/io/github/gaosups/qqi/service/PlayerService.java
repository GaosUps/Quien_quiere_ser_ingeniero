package io.github.gaosups.qqi.service;

import io.github.gaosups.qqi.model.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerService {

	List<Player> findAll();

	Optional<Player> findById(UUID id);

	Player save(Player player);

	void updateUserById(UUID id, Player player);

	void deleteById(UUID id);

	void delete(Player player);
}