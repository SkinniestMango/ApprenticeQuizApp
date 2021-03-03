import co.uk.apprenticeship.model.Answer;
import co.uk.apprenticeship.model.Question;
import co.uk.apprenticeship.repository.AnswerRepository;
import co.uk.apprenticeship.services.AnswerService;
import co.uk.apprenticeship.services.AnswerServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestAnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Test
    public void testCreateAnswer(){
        Question question = new Question();
        question.setId(1L);
        Answer answer = new Answer("Here is a Question", question, false);

        AnswerService answerService = new AnswerServiceImpl(answerRepository);

        answerService.createAnswer(answer);

    }
}
