package io.github.gaosups.qqi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Table(name = "players")
public class Player
	implements Comparable<Player> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "user_id", updatable = false)
	private final UUID uuid;

	@NotBlank(message = "{NotBlank.player.fullName}")
	@NotNull(message = "{NotNull.player.fullName}")
	@Column(name = "username", length = 60)
	private String username;

	@Transient
	private byte points;

	@Transient
	private long lastAnswerTime;

	public Player(final String username) {
		this.uuid = UUID.randomUUID();
		this.username = username;
		this.points = 0;
		this.lastAnswerTime = 0;
	}

	public Player() {
		this.uuid = UUID.randomUUID();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Player player)) {
			return false;
		}
		return Objects.equals(getUuid(), player.getUuid());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getUuid());
	}

	@Override
	public int compareTo(final @org.jetbrains.annotations.NotNull Player otherPlayer) {
		return Comparator.comparing(Player::getPoints)
			       .thenComparing(Player::getUsername)
			       .compare(this, otherPlayer);
	}

	public void incrementPoints() {
		this.points++;
	}
}
