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
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @GetMapping("/admin/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createQuiz(){
        return "createQuiz";
    }

    @PostMapping("/admin/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createQuiz(Quiz quiz){
        quiz = quizService.save(quiz);
        return "redirect:/quiz/admin/editQuiz/" + quiz.getId();
    }

    @GetMapping("/admin/editQuiz/{quiz_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editQuiz(@PathVariable long quiz_id, Model model){
        Quiz quiz = quizService.findById(quiz_id);
        model.addAttribute("quiz", quiz);
        return "editQuiz";
    }

    @PostMapping("/admin/editQuiz/{quiz_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editQuiz(@PathVariable long quiz_id, Quiz quiz, Model model){
        Quiz oldQuiz = quizService.findById(quiz_id);
        oldQuiz.setName(quiz.getName());
        oldQuiz.setDescription(quiz.getDescription());
        quiz = quizService.update(oldQuiz);
        model.addAttribute("quiz", quiz);
        return "editQuiz";
    }

    @GetMapping("/admin/deleteQuiz/{quiz_id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteQuiz(@PathVariable long quiz_id){
        Quiz quiz = quizService.findById(quiz_id);
        quizService.delete(quiz);
        return "forward:/home";
    }

    @GetMapping("/play/{quiz_id}")
    public String playQuiz(@PathVariable long quiz_id, Model model){
        Quiz quiz = quizService.findById(quiz_id);
        model.addAttribute("quiz", quiz);
        return "playQuiz";
    }

    @GetMapping("/play/{quiz_id}/submitQuizAnswers")
    public String submitAnswers(@PathVariable long quiz_id, @RequestParam Map<String, String> submitted, Model model){
        int correct = 0;
        int total = 0;

        for(Map.Entry<String, String> entry: submitted.entrySet()){
            Long answerId = Long.parseLong(entry.getValue());
            Answer answer = answerService.findById(answerId);

            if(answer.getCorrect()){
                correct++;
            }
            total++;
        }
        model.addAttribute("result", "You got " + correct + " out of " + total + "!");
        return "resultQuiz";
    }

    @PostMapping("/admin/editQuestion/{question_id}")
    @PreAuthorize("hasAuthority('ADMIN'")
    public String editQuestion(@PathVariable long question_id, Question question){
        Question oldQuestion = questionService.findById(question_id);
        oldQuestion.setTitle(question.getTitle());
        oldQuestion.setQuestionText(question.getQuestionText());
        question = questionService.update(oldQuestion);
        Quiz quiz = question.getQuiz();
        return "redirect:/quiz/admin/editQuiz/" + quiz.getId();
    }

    @PostMapping("/admin/editQuiz/{quiz_id}/addQuestion")
    @PreAuthorize("hasAuthority('ADMIN'")
    public String addNewQuestion(@PathVariable long quiz_id, Question question, Model model){
        Quiz quiz = quizService.findById(quiz_id);
        question.setQuiz(quiz);
        question = questionService.save(question);
        return "redirect:/quiz/admin/editQuiz/" + quiz.getId();
    }

}
