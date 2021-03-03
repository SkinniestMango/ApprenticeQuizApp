package co.uk.apprenticeship.repository;

import co.uk.apprenticeship.model.Answer;
import co.uk.apprenticeship.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository("answerRepository")
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Collection<Answer> findByQuestion(Question question);
}
