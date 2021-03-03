package co.uk.apprenticeship.services;

import co.uk.apprenticeship.model.Question;
import co.uk.apprenticeship.model.Quiz;

import java.util.Collection;

public interface QuizService {

    Collection<Quiz> findAll();

    Quiz save(Quiz quiz);

    Quiz findById(Long id);

    Quiz update(Quiz quiz);

    void delete(Quiz quiz);

}
