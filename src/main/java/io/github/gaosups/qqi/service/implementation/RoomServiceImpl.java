package io.github.gaosups.qqi.service.implementation;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.Room;
import io.github.gaosups.qqi.model.RoomStatus;
import io.github.gaosups.qqi.model.exceptions.TriviaRoomNotFoundException;
import io.github.gaosups.qqi.repository.RoomRepository;
import io.github.gaosups.qqi.service.QuestionService;
import io.github.gaosups.qqi.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl
	implements RoomService {

	private final RoomRepository roomRepository;
	private final QuestionService questionService;
	@Override
	public Optional<Room> getTriviaRoomById(final UUID id) {
		return roomRepository.findById(id);
	}

	@Override
	public Room startGame(final String uuid) {
		Optional<Room> room = getTriviaRoomById(UUID.fromString(uuid));

		if (room.isEmpty()) {
			throw new TriviaRoomNotFoundException(
				"La sala de trivia con el id " + uuid + " no se ha encontrado."
			);
		}

		Room room1 = room.get();


		room1.setStatus(RoomStatus.STARTING);
		roomRepository.save(room1);

		return room1;
	}

	@Override
	public void submitAnswer(final String uuid, final Player player) {
		Optional<Room> room = getTriviaRoomById(UUID.fromString(uuid));
		Room room1 = room.orElseThrow(
			() -> new TriviaRoomNotFoundException(
				"La sala de trivia con el id " + uuid + " no se ha encontrado.")
		);


	}

	@Override
	public void addPlayerToRoom(final String uuid, final Player player) {
		Optional<Room> optionalRoom = getTriviaRoomById(UUID.fromString(uuid));
		Room room = optionalRoom.orElseThrow(
			() -> new TriviaRoomNotFoundException(
				"La sala de trivia con el id " + uuid + " no se ha encontrado."
			));

		if(room.getPlayer1() != null && room.getPlayer2() != null) {
			throw new TriviaRoomNotFoundException(
				"La sala de trivia con el id " + uuid + " está llena."
			);
		}

		if(room.getPlayer1() == null) {
			room.setPlayer1(player);
		} else {
			room.setPlayer2(player);
		}

		roomRepository.save(room);
	}

	@Override
	public RoomStatus getRoomStatus(final String uuid) {
		return getTriviaRoomById(UUID.fromString(uuid))
			       .orElseThrow(
				       () -> new TriviaRoomNotFoundException(
					       "La sala de trivia con el id " + uuid + " no se ha encontrado."
				       ))
			       .getStatus();
	}
}
