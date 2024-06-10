package io.github.gaosups.qqi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "id", updatable = false)
	private UUID id;

	@OneToOne
	@JoinColumn(name = "player1_id", referencedColumnName = "id")
	private Player player1;

	@OneToOne
	@JoinColumn(name = "player2_id", referencedColumnName = "id")
	private Player player2;

	@OneToMany
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private List<Question> questions;

	@Enumerated(EnumType.STRING)
	private RoomStatus status;


	public Room() {
		this.status = RoomStatus.WAITING;
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
