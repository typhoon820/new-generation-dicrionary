package com.assessment.project.service;

import com.assessment.project.model.UserEntity;
import com.assessment.project.model.WordEntity;

import java.util.List;

/**
 * Created by Никита on 25.05.2017.
 */
public interface WordService {
    WordEntity findById(int id);
    void addWord(String word, String translation, UserEntity user);
    void saveWord(WordEntity word);
    void deleteWord(int id);
    List<WordEntity> findAllWords();
    WordEntity findByWordAndUser(String word, UserEntity userEntity);
}
