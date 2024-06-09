package io.github.gaosups.qqi.service.implementation;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.Question;
import io.github.gaosups.qqi.model.Room;
import io.github.gaosups.qqi.model.dto.QuestionDTO;
import io.github.gaosups.qqi.model.exceptions.TriviaRoomNotFoundException;
import io.github.gaosups.qqi.repository.QuestionRepository;
import io.github.gaosups.qqi.repository.RoomRepository;
import io.github.gaosups.qqi.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl
	implements RoomService {

	private final RoomRepository roomRepository;
	private final QuestionRepository questionRepository;
	private final MessageSource messageSource;

	@Override
	public Optional<Room> getTriviaRoomById(final UUID id) {
		return roomRepository.findById(id);
	}

	@Override
	public Room startGame(final String uuid) {
		Optional<Room> room = getTriviaRoomById(UUID.fromString(uuid));

		if (room.isEmpty()) {
			throw new TriviaRoomNotFoundException(messageSource.getMessage(
				"NotFound.trivia.room.message",
				new String[] { uuid },
				Locale.US));
		}

		Room room1 = room.get();

		List<Question> questions = questionRepository.findRandomQuestions();
		room1.setQuestions(questions);
		roomRepository.save(room1);

		return room1;
	}

	@Override
	public void submitAnswer(final String uuid, final Player player, final QuestionDTO answer) {
	}

	@Override
	public void addPlayerToRoom(final String uuid, final Player player) {



	}

	@Override
	public Room getRoomStatus(final String uuid) {
		return null;
	}

}
