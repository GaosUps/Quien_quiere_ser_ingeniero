package io.github.gaosups.qqi.repository;

import io.github.gaosups.qqi.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing {@link Player} entities.
 *
 * <p>This interface extends {@link CrudRepository} and {@link PagingAndSortingRepository} to provide CRUD operations and paging/sorting capabilities.</p>
 *
 * @since 0.1.0-alpha-1
 * @version 0.1.0-alpha-1
 *
 * @see Player
 * @see CrudRepository
 * @see PagingAndSortingRepository
 * @see UUID
 *
 * @author ImEcuadorian
 */
@Repository
public interface PlayerRepository
	extends CrudRepository<Player, UUID>,
	        PagingAndSortingRepository<Player, UUID> {
}
