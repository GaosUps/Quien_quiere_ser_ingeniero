package io.github.gaosups.qqi.controller;

import io.github.gaosups.qqi.model.Player;
import io.github.gaosups.qqi.model.Room;
import io.github.gaosups.qqi.model.dto.QuestionDTO;
import io.github.gaosups.qqi.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TriviaController {

	private final RoomService roomService;
	private final SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/startGame")
	@SendTo("/topic/game")
	public Room startGame(String uuid) {
		return roomService.startGame(uuid);
	}

	@MessageMapping("/answer")
	public void answerQuestion(String uuid, Player player, QuestionDTO questionDTO) {
		roomService.submitAnswer(uuid, player, questionDTO);
		//TODO Notify the room about the new state
		messagingTemplate.convertAndSend("/topic/game/answer", player);
	}

	@MessageMapping("/joinRoom")
	public void joinRoom(String uuid, Player player) {
		roomService.addPlayerToRoom(uuid, player);
		messagingTemplate.convertAndSend("/topic/room/join" + uuid, roomService.getRoomStatus(uuid));
	}
}
