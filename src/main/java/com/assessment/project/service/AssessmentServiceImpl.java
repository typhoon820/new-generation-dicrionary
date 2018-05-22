package com.assessment.project.service;

import com.assessment.project.DAO.WordDAO;
import com.assessment.project.component.Question;
import com.assessment.project.model.UserEntity;
import com.assessment.project.model.WordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("AssessmentService")
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    private WordDAO wordDAO;
    private Random rng;

    @Override
    public List<Question> generateQuestions(UserEntity userEntity, int questionCount) {
        rng = new Random(System.currentTimeMillis());
        List<Question> questions = new ArrayList<>();
        Set<WordEntity> wordPool = new LinkedHashSet<>();
        int dictionarySize = userEntity.getDictionary().size();
        int count = (4 * questionCount > dictionarySize) ? dictionarySize / 4 : questionCount;
        for (int i = 0; i < count * 4; i++) {
            wordPool.add(userEntity.getDictionary().get(i));
        }

        List<WordEntity> words = new ArrayList<>(wordPool);
        for (int i = 0; i < count; i++) {
            Question question = new Question();
            int index = rng.nextInt(words.size());
            WordEntity word = words.remove(index);
//            words.remove(0);
            List<String> answers = new ArrayList<>();
            answers.add(word.getMeanings().get(0).getTranslation());
            answers.add(words.remove(rng.nextInt(words.size())).getMeanings().get(0).getTranslation());
            answers.add(words.remove(rng.nextInt(words.size())).getMeanings().get(0).getTranslation());
            answers.add(words.remove(rng.nextInt(words.size())).getMeanings().get(0).getTranslation());
//            for (int j = 0; j < 3; j++)
//                words.remove(0);

            question.setQuestionWord(word.getWord());
            Collections.shuffle(answers);
            question.setAnswers(answers);
            question.setCorrect(word.getMeanings().get(0).getTranslation());
            questions.add(question);
        }
        return questions;
    }

    @Override
    public int countCorrectAnswers(List<Question> questionList) {
        int count = 0;
        for (Question q : questionList) {
            if (q.getChosen().equals(q.getCorrect())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String evaluateResult(List<Question> questionList) {
        String result = "That won't do. You'd better do a vocabulary revision";
        int correct = countCorrectAnswers(questionList);
        int total = questionList.size();
        if (correct > total * 0.5) {
            if (correct > 0.85){
                result = "Great job. Your vocabulary is pretty good.";
            }
            else {
                result = "Pretty good. Keep working hard, and the world is your oyster";
            }
        }
        else {
            if(correct>total*0.3){
                result = "Not bad, but not perfect. You'd better pay more attention to unknown words.";
            }
        }
        return result;
    }
}
