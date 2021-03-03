package co.uk.apprenticeship.services;

import co.uk.apprenticeship.model.Question;


public interface QuestionService {

    Question save(Question question);

    Question findById(Long id);

    Question update(Question question);

    void delete(Question question);

}
