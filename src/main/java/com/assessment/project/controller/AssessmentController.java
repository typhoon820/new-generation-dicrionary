package com.assessment.project.controller;

import com.assessment.project.component.Question;
import com.assessment.project.model.UserEntity;
import com.assessment.project.service.AssessmentService;
import com.assessment.project.service.UserService;
import com.assessment.project.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class AssessmentController {

    private Question question;

    @Autowired
    private UserService userService;

    @Autowired
    private WordService wordService;

    @Autowired
    private AssessmentService assessmentService;

    private UserEntity currentUser;

    private int questionCount = 15;
    private int questionsAnswered = 0;
    private List<Question> questionList;

    @GetMapping("/test")
    public ModelAndView getTest() {

        if (questionsAnswered == 0){
            //todo: fetch 4 * 15 words
            //shuffle answwers
            SecurityContext context = SecurityContextHolder.getContext();
            currentUser = userService.findByLogin(context.getAuthentication().getName());
            questionList = assessmentService.generateQuestions(currentUser, questionCount);
        }
        question = questionList.get(questionsAnswered);
        ModelAndView model = new ModelAndView("assessment");
        model.addObject("currentQuestion", questionsAnswered+1);
        model.addObject("totalQuestions", questionList.size());
        model.addObject("question", question);
        return model;
    }

    @PostMapping("/test")
    public ModelAndView submitAnswer(@ModelAttribute("question") Question answer, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        questionList.get(questionsAnswered).setChosen(answer.getChosen());
        questionsAnswered++;
        if(questionsAnswered >= questionList.size()){
            questionsAnswered = 0;
            model.setViewName("redirect:/test/results");
        }
        else {
            model.setViewName("redirect:/test");
        }
        return model;
    }

    @GetMapping("/test/results")
    public ModelAndView getResults() {
        ModelAndView model = new ModelAndView();
        if(questionList == null){
            model.addObject("message", "Seems that you haven't done the test yet. Wanna try it?");
            model.setViewName("fail-result");
            return model;
        }
        model.addObject("correctAnswers", assessmentService.countCorrectAnswers(questionList));
        model.addObject("totalQuestions", questionList.size());
        model.addObject("result", assessmentService.evaluateResult(questionList));
        model.addObject("questionList",questionList);
        model.setViewName("results");
        return model;
    }
}
