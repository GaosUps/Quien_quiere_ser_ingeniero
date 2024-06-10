package io.github.gaosups.qqi.repository;

import io.github.gaosups.qqi.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository
	extends CrudRepository<Question, Long> {
	@Query("SELECT q FROM Question q WHERE q.categoryId.name = :categoryName")
	List<Question> findByCategoryName(String categoryName);

	@Query("SELECT q FROM Question q ORDER BY RAND() limit 10")
	List<Question> findQuestionsRandom();

	List<Question> findQuestionByDifficulty(int difficulty);
}
