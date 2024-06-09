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

/**
 * The Player class represents a player in a game. It contains information such as the unique
 * identifier, username, email, points, state, and last answer time of the player. The class also
 * provides methods for comparing players and checking for equality.
 *
 * <p>This interface is a member of the Java Collections Framework.</p>
 *
 * @author ImEcuadorian
 * @see Comparator
 * @see UUID
 * @see LocalDateTime
 * @since 0.1.0-alpha-1
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "players")
public class Player
	implements Comparable<Player> {

	@Id
	@GeneratedValue
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "user_id", updatable = false)
	private final UUID uuid;

	@NotBlank(message = "{NotBlank.player.fullName}")
	@NotNull(message = "{NotNull.player.fullName}")
	@Column(name = "username", length = 60)
	private String username;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Transient
	private byte points;

	@Transient
	private long lastAnswerTime;

	/**
	 * Creates a new Player object with the provided username, email, and password.
	 *
	 * @param username
	 * 	the username of the player
	 * @param email
	 * 	the email of the player
	 * @param password
	 * 	the password of the player
	 */
	public Player(final String username) {
		this.uuid = UUID.randomUUID();
		this.username = username;
		this.points = 0;
		this.lastAnswerTime = 0;
	}

	public Player() {
		this.uuid = UUID.randomUUID();
	}

	/**
	 * Sets the createDate field to the current date and time before persisting the entity.
	 */
	@PrePersist
	protected void prePersist() {
		createDate = LocalDateTime.now();
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

	/**
	 * Compares this player with the specified player for order. Players are ordered first by their
	 * points in ascending order, and then by their usernames in ascending order. Returns a negative
	 * integer, zero, or a positive integer as this player is less than, equal to, or greater than
	 * the
	 * specified player.
	 *
	 * @param otherPlayer
	 * 	the player to be compared
	 *
	 * @return a negative integer, zero, or a positive integer as this player is less than, equal to,
	 * 	or greater than the specified player
	 */
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
