package com.assessment.project.DAO;

import com.assessment.project.model.UserEntity;
import com.assessment.project.model.WordEntity;

import java.util.List;

/**
 * Created by Никита on 25.05.2017.
 */
public interface WordDAO {

    WordEntity findById(int id);
    void saveWord(WordEntity song);
    void deleteWord(int id);
    List<WordEntity> findAllWords();
    WordEntity findByWordAndUser(String word, UserEntity userEntity);
}
