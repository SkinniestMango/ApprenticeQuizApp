package co.uk.apprenticeship.services;

import co.uk.apprenticeship.model.Answer;
import co.uk.apprenticeship.model.Question;

import java.util.Collection;

public interface AnswerService {

    Answer createAnswer(Answer answer);

    Answer findById(Long id);

    Answer updateAnswer(Answer answer);

    void deleteAnswer(Answer answer);

    Answer mergeAnswerForUpdate(Answer oldAnswer, Answer updatedAnswer);
}
