package co.uk.apprenticeship.web;

import co.uk.apprenticeship.model.Quiz;
import co.uk.apprenticeship.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class HomeController {

    @Autowired
    QuizService quizService;

    @GetMapping({"/", "/home"})
    public String home(Model model){
        Collection<Quiz> quizzes = quizService.findAll();
        if(quizzes.isEmpty()){
            quizzes.add(new Quiz());
        }
        model.addAttribute("quizzes", quizzes);
        return "index";
    }



}
