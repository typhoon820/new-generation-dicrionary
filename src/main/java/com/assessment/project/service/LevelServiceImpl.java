package com.assessment.project.service;

import com.assessment.project.DAO.LevelDAO;
import com.assessment.project.model.LevelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Никита on 27.05.2017.
 */
@Service("levelService")
@Transactional
public class LevelServiceImpl implements LevelService {
    @Autowired
    LevelDAO levelDAO;
    @Override
    public List<LevelEntity> findAllLevels() {
        return levelDAO.findAllLevels();
    }
}
