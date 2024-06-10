package io.github.gaosups.qqi.service.implementation;

import io.github.gaosups.qqi.model.Question;
import io.github.gaosups.qqi.repository.QuestionRepository;
import io.github.gaosups.qqi.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionServiceImpl
	implements QuestionService {

	private final QuestionRepository questionRepository;

	@Override
	public List<Question> getQuestionsByCategory(final String categoryName) {
		return questionRepository.findByCategoryName(categoryName);
	}

	@Override
	public Optional<Question> getQuestionById(final Long id) {
		return questionRepository.findById(id);
	}

	@Override
	public boolean isCorrectAnswer(final Long questionId, final String answer) {
		Optional<Question> question = getQuestionById(questionId);
		return question.map(value -> value.getCorrectAnswer()
			                             .equalsIgnoreCase(answer))
			       .orElse(false);
	}

	@Override
	public List<Question> findQuestionByDifficulty(final int difficulty) {
		return questionRepository.findQuestionByDifficulty(difficulty);
	}

	@Override
	public List<Question> findQuestionsRandom() {
		return questionRepository.findQuestionsRandom();
	}

	@Override
	public List<Question> findByCategoryName(final String categoryName) {
		return questionRepository.findByCategoryName(categoryName);
	}
}
