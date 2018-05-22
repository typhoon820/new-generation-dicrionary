package com.assessment.project.component;

/**
 * Created by Никита on 26.05.2017.
 */
public class Dictionary {
    public String word;
    public String translation;


    public Dictionary(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getWord() {

        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
