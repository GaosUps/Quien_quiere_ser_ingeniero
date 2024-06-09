package io.github.gaosups.qqi.service;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.Room;
import io.github.gaosups.qqi.model.dto.QuestionDTO;

import java.util.Optional;
import java.util.UUID;

public interface RoomService {

	Optional<Room> getTriviaRoomById(UUID id);
	Room startGame(String uuid);

	void submitAnswer(String uuid, Player player, QuestionDTO answer);

	void addPlayerToRoom(String uuid, Player player);

	Room getRoomStatus(String uuid);

}
