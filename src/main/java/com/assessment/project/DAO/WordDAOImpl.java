package com.assessment.project.DAO;

import com.assessment.project.model.UserEntity;
import com.assessment.project.model.WordEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("WordDAO")
public class WordDAOImpl extends AbstractDAO<Integer, WordEntity> implements WordDAO{
    @Override
    public WordEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(WordEntity.class);
        criteria.add(Restrictions.eq("idUser",id));
        return (WordEntity)criteria.uniqueResult();
    }

    @Override
    public void saveWord(WordEntity song) {
//        getSession().save(song);
        getSession().saveOrUpdate(song);
    }

    @Override
    public void deleteWord(int id) {
        Query query = getSession().createSQLQuery("delete from word where ID = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    @Override
    public List<WordEntity> findAllWords() {
        Criteria criteria = getSession().createCriteria(WordEntity.class);
        return (List<WordEntity>) criteria.list();
    }

    @Override
    public WordEntity findByWordAndUser(String word, UserEntity userEntity) {
        Criteria criteria = getSession().createCriteria(WordEntity.class);
        criteria.add(Restrictions.and(Restrictions.eq("word", word),
                Restrictions.eq("user", userEntity)));
        return (WordEntity) criteria.uniqueResult();
    }
}
