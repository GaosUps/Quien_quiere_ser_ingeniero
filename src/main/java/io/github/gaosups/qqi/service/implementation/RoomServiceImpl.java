package io.github.gaosups.qqi.service.implementation;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.Question;
import io.github.gaosups.qqi.model.Room;
import io.github.gaosups.qqi.model.RoomStatus;
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

		if(room1.checkSize()){
			throw new TriviaRoomNotFoundException(messageSource.getMessage(
				"NotFound.trivia.room.message",
				new String[] { uuid },
				Locale.US));
		}

		List<Question> questions = questionRepository.findRandomQuestions();
		room1.setQuestions(questions);


		room1.setStatus(RoomStatus.STARTING);
		room1.setCurrentQuestionIndex(0);
		roomRepository.save(room1);

		return room1;
	}

	@Override
	public void submitAnswer(final String uuid, final Player player, final QuestionDTO answer) {
		Optional<Room> room = getTriviaRoomById(UUID.fromString(uuid));
		Room room1 = room.orElseThrow(
			() -> new TriviaRoomNotFoundException(messageSource.getMessage(
				"NotFound.trivia.room.message",
				new String[] { uuid },
				Locale.US)));

		Question question = room1.getCurrentQuestion();
		if (question.getCorrectAnswerIndex() == answer.getAnswer().indexOf(answer.getAnswer())) {
			player.setPoints((byte) (player.getPoints() + 1));
		}

		room1.setCurrentQuestionIndex(room1.getCurrentQuestionIndex() + 1);
		if (room1.getCurrentQuestionIndex() == room1.getQuestions().size()) {
			room1.setStatus(RoomStatus.FINISH);
		}
		roomRepository.save(room1);
	}

	@Override
	public void addPlayerToRoom(final String uuid, final Player player) {
		Optional<Room> optionalRoom = getTriviaRoomById(UUID.fromString(uuid));
		Room room = optionalRoom.orElseThrow(
			() -> new TriviaRoomNotFoundException(messageSource.getMessage(
				"NotFound.trivia.room.message",
				new String[] { uuid },
				Locale.US)));

		if (room.isFull()) {
			throw new TriviaRoomNotFoundException(messageSource.getMessage(
				"NotFound.trivia.room.message",
				new String[] { uuid },
				Locale.US));
		}

		room.getPlayers().add(player);
		roomRepository.save(room);
	}

	@Override
	public Room getRoomStatus(final String uuid) {
		return getTriviaRoomById(UUID.fromString(uuid))
			.orElseThrow(
				() -> new TriviaRoomNotFoundException(messageSource.getMessage(
					"NotFound.trivia.room.message",
					new String[] { uuid },
					Locale.US)));
	}

}
