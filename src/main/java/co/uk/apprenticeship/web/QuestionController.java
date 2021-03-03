package co.uk.apprenticeship.web;

import co.uk.apprenticeship.model.Answer;
import co.uk.apprenticeship.model.Question;
import co.uk.apprenticeship.model.Quiz;
import co.uk.apprenticeship.services.AnswerService;
import co.uk.apprenticeship.services.QuestionService;
import co.uk.apprenticeship.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuizService quizService;

    @Autowired
    AnswerService answerService;

    @GetMapping("/admin/{question_id}/deleteQuestion")
    @PreAuthorize("hasAuthority('ADMIN'")
    public String deleteQuestion(@PathVariable long question_id){
        Question question = questionService.findById(question_id);
        Quiz quiz = question.getQuiz();
        questionService.delete(question);
        return "redirect:/quiz/admin/editQuiz/" + quiz.getId();
    }

    @GetMapping("/admin/{question_id}/editAnswers")
    @PreAuthorize("hasAuthority('ADMIN'")
    public String editAnswers(@PathVariable long question_id, Model model){
        Question question = questionService.findById(question_id);
        model.addAttribute("question", question);
        return "editQuestion";
    }

    @PostMapping("/admin/{question_id}/editAnswer/{answer_id}")
    @PreAuthorize("hasAuthority('ADMIN'")
    public String editAnswer(@PathVariable long question_id, @PathVariable long answer_id, Answer answer){
        Answer originalAnswer = answerService.findById(answer_id);
        originalAnswer = answerService.mergeAnswerForUpdate(originalAnswer, answer);
        answer = answerService.updateAnswer(originalAnswer);
        Question question = questionService.findById(question_id);
        return "redirect:/question/admin/"+question.getId()+"/editAnswers";
    }

    @PostMapping("/admin/{question_id}/addAnswer")
    @PreAuthorize("hasAuthority('ADMIN'")
    public String editAnswer(@PathVariable long question_id, Answer answer){
        Question question = questionService.findById(question_id);
        if(answer.getCorrect() == null){
            answer.setCorrect(false);
        }
        answer.setQuestion(question);
        answer = answerService.createAnswer(answer);
        return "redirect:/question/admin/"+question.getId()+"/editAnswers";
    }

    @GetMapping("/admin/{question_id}/deleteAnswer/{answer_id}")
    @PreAuthorize("hasAuthority('ADMIN'")
    public String deleteAnswer(@PathVariable long question_id, @PathVariable long answer_id){
        Answer answer = answerService.findById(answer_id);
        answerService.deleteAnswer(answer);
        return "redirect:/question/admin/"+question_id+"/editAnswers";
    }

}
