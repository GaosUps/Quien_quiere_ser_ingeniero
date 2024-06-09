package io.github.gaosups.qqi.repository;

import io.github.gaosups.qqi.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerRepository
	extends CrudRepository<Player, UUID>,
	        PagingAndSortingRepository<Player, UUID> {
}
