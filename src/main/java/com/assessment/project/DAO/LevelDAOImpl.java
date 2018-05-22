package com.assessment.project.DAO;


import com.assessment.project.model.LevelEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("levelDAO")
public class LevelDAOImpl extends AbstractDAO<Integer, LevelEntity> implements LevelDAO {
    @Override
    public List<LevelEntity> findAllLevels() {
        Criteria criteria = getSession().createCriteria(LevelEntity.class);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<LevelEntity>)criteria.list();
    }
}
