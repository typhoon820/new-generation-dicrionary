package com.assessment.project.service;

import com.assessment.project.DAO.TranslationDAO;
import com.assessment.project.DAO.WordDAO;
import com.assessment.project.model.TranslationEntity;
import com.assessment.project.model.UserEntity;
import com.assessment.project.model.WordEntity;
import com.mysql.jdbc.exceptions.jdbc4.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service("WordService")
@Transactional
public class WordServiceImpl implements WordService {
    private static final Logger logger = LoggerFactory.getLogger(WordServiceImpl.class);
    @Autowired
    WordDAO wordDAO;
    @Autowired
    TranslationDAO translationDAO;

    @Override
    public WordEntity findById(int id) {
        return wordDAO.findById(id);
    }

    @Override
    public void addWord(String word, String translation, UserEntity user) {
        WordEntity w = wordDAO.findByWordAndUser(word, user);
        logger.info(word + "    =====    " + translation);
//        WordEntity w = new WordEntity();
//        TranslationEntity t = translationDAO.findByName(translation);
//        List<TranslationEntity> ms = w.getMeanings();
        TranslationEntity t = new TranslationEntity();
        if (w == null) {
            w = new WordEntity();
            w.setUser(user);
            w.setWord(word);
        }
        t.setTranslation(translation);
        t.setNativeWord(w);

        if (w.getMeanings().stream()
                .noneMatch(wrd->wrd.getTranslation().toLowerCase().equals(t.getTranslation().toLowerCase()))) {
            w.getMeanings().add(t);
        }

        wordDAO.saveWord(w);

    }

    @Override
    public void saveWord(WordEntity word) {
        wordDAO.saveWord(word);
    }

    @Override
    public void deleteWord(int id) {
        wordDAO.deleteWord(id);
    }

    @Override
    public List<WordEntity> findAllWords() {
        return wordDAO.findAllWords();
    }

    @Override
    public WordEntity findByWordAndUser(String word, UserEntity userEntity) {
        return wordDAO.findByWordAndUser(word, userEntity);
    }
}
