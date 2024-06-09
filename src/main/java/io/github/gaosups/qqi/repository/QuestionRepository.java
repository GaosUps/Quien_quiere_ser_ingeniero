package io.github.gaosups.qqi.repository;

import io.github.gaosups.qqi.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository
	extends CrudRepository<Question, UUID> {
	@Query("SELECT q FROM Question q order by RANDOM()")
	List<Question> findRandomQuestions();
}
