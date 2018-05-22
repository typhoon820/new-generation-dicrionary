package com.assessment.project.component;


import java.util.Map;

public class WordStat {
    private String text;
    private int wordsCount;
    private Map<String, Integer> frequency;
    public WordStat(){
        super();
    }

    public WordStat(String text) {
        this.text = text;
    }

    public int getWordsCount() {
        return wordsCount;

    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    public Map<String, Integer> getFrequency() {
        return frequency;
    }

    public void setFrequency(Map<String, Integer> frequency) {
        this.frequency = frequency;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
