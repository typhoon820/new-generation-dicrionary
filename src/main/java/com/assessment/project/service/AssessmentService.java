package com.assessment.project.service;

import com.assessment.project.component.Question;
import com.assessment.project.model.UserEntity;

import java.util.List;

public interface AssessmentService {
    List<Question> generateQuestions(UserEntity userEntity, int questionCount);
    int countCorrectAnswers(List<Question> questionList);
    String evaluateResult(List<Question> questionList);
}
