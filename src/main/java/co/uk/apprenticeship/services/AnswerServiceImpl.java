package co.uk.apprenticeship.services;

import co.uk.apprenticeship.model.Answer;
import co.uk.apprenticeship.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("answerService")
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository){
        super();
        this.answerRepository = answerRepository;
    }

    @Override
    public Answer createAnswer(Answer answer) {
        return this.answerRepository.save(answer);
    }

    @Override
    public Answer findById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    @Override
    public Answer updateAnswer(Answer newAnswer) {
        return answerRepository.save(newAnswer);
    }

    @Override
    public void deleteAnswer(Answer answer) {
        answerRepository.delete(answer);
    }

    @Override
    public Answer mergeAnswerForUpdate(Answer oldAnswer, Answer updatedAnswer){
        if(updatedAnswer.getCorrect() == null){
            updatedAnswer.setCorrect(false);
        }
        oldAnswer.setText(updatedAnswer.getText());
        oldAnswer.setCorrect(updatedAnswer.getCorrect());
        return oldAnswer;
    }
}
