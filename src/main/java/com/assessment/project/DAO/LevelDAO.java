package com.assessment.project.DAO;

import com.assessment.project.model.LevelEntity;

import java.util.List;

/**
 * Created by Никита on 27.05.2017.
 */
public interface LevelDAO {
    List<LevelEntity> findAllLevels();
}
