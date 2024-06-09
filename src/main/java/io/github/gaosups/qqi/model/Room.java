package io.github.gaosups.qqi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "trivia_room")
@Getter
@Setter
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "trivia_id", updatable = false)
	private UUID id;

	@OneToMany
	@JoinColumn(name = "room_id")
	private List<Player> players = new ArrayList<>();

	@OneToMany
	@JoinColumn(name = "room_id")
	private List<Question> questions = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private RoomStatus status;

	private int currentQuestionIndex;


	public Room() {
		this.status = RoomStatus.WAITING;
	}

	public Question getCurrentQuestion() {
		return questions.get(currentQuestionIndex);
	}

	public boolean isFull() {
		return players.size() >= 2;
	}

	public boolean checkSize() {
		return players.size() < 2;
	}
	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof final Room room))
			return false;
		return Objects.equals(getId(), room.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}
}
