package io.github.gaosups.qqi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "answers")
public class Answer {

	@Id
	@GeneratedValue
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "answer_id", updatable = false)
	private Long id;

	@NotBlank
	private String answerText;
}