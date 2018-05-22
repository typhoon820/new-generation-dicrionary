package com.assessment.project.DAO;

import com.assessment.project.model.TranslationEntity;

import java.util.List;

/**
 * Created by Никита on 25.05.2017.
 */
public interface TranslationDAO {
    TranslationEntity findById(int id);
    TranslationEntity findByName(String name);
    void saveAuthor (TranslationEntity author);
    void deleteAuthor(int id);
    List<TranslationEntity> findAllAuthors();
}
