package io.github.gaosups.qqi.repository;

import io.github.gaosups.qqi.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository
	extends CrudRepository<Room, UUID> {
}
