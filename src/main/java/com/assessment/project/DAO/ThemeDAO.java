package com.assessment.project.DAO;

import com.assessment.project.model.ThemeEntity;
import com.assessment.project.model.TranslationEntity;

import java.util.List;

/**
 * Created by Никита on 25.05.2017.
 */
public interface ThemeDAO {
    ThemeEntity findById(int id);
    ThemeEntity findByName(String name);
    void saveAuthor (ThemeEntity author);
    void deleteAuthor(int id);
    List<ThemeEntity> findAllAuthors();
}
