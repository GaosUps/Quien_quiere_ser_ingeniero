package io.github.gaosups.qqi.service;

import io.github.gaosups.qqi.model.Question;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

	List<Question> getQuestionsByCategory(String categoryName);
	Optional<Question> getQuestionById(Long id);
	boolean isCorrectAnswer(Long questionId, String answer);

	List<Question> findByCategoryName(String categoryName);
	List<Question> findQuestionsRandom();
	List<Question> findQuestionByDifficulty(int difficulty);
}
