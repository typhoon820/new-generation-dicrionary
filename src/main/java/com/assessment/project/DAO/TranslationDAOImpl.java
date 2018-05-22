package com.assessment.project.DAO;

import com.assessment.project.model.TranslationEntity;
import com.assessment.project.model.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Repository("TranslationDAO")
public class TranslationDAOImpl extends AbstractDAO<Integer,TranslationEntity> implements TranslationDAO {
    @Override
    public TranslationEntity findById(int id) {
        return null;
    }

    @Override
    public TranslationEntity findByName(String name) {
        Criteria criteria = getSession().createCriteria(TranslationEntity.class);
        return (TranslationEntity) criteria.add(Restrictions.eq("translation",name)).uniqueResult();
    }

    @Override
    public void saveAuthor(TranslationEntity author) {
        try {
            getSession().saveOrUpdate(author);
        }
        catch (ConstraintViolationException e){

        }
    }

    @Override
    public void deleteAuthor(int id) {

    }

    @Override
    public List<TranslationEntity> findAllAuthors() {
        return null;
    }
}
