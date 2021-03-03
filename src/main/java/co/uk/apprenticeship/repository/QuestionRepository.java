package co.uk.apprenticeship.repository;

import co.uk.apprenticeship.model.Question;
import co.uk.apprenticeship.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("questionRepository")
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Collection<Question> findByQuiz(Quiz quiz);
}
