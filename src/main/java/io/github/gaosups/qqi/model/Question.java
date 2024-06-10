package io.github.gaosups.qqi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category categoryId;

	private int difficulty;

	private String question;

	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;

	private String correctAnswer;
}
