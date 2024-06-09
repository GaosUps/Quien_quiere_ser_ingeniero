package io.github.gaosups.qqi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question {

	@Id
	@GeneratedValue
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "question_id", updatable = false)
	private UUID id;

	@NotBlank
	private String question;

	@OneToMany
	private List<Answer> answers;

	@NotNull
	private int correctAnswerIndex;

}
