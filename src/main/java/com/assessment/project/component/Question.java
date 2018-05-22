package com.assessment.project.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {
    private String questionWord = "";
    private List<String> answers = new ArrayList<>(4);
    private String chosen = "";
    private String correct = "";

    public Question(String questionWord, List<String> answers, String chosen, String correct) {
        this.questionWord = questionWord;
        this.answers = answers;
        this.chosen = chosen;
        this.correct = correct;
    }

    public Question() {
    }


    public String getChosen() {
        return chosen;
    }

    public void setChosen(String chosen) {
        this.chosen = chosen;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getQuestionWord() {
        return questionWord;
    }

    public void setQuestionWord(String questionWord) {
        this.questionWord = questionWord;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (!questionWord.equals(question.questionWord)) return false;
        if (!answers.equals(question.answers)) return false;
        if (!chosen.equals(question.chosen)) return false;
        return correct.equals(question.correct);
    }

    @Override
    public int hashCode() {
        int result = questionWord.hashCode();
        result = 31 * result + answers.hashCode();
        result = 31 * result + chosen.hashCode();
        result = 31 * result + correct.hashCode();
        return result;
    }
}
