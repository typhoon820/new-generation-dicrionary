package com.assessment.project.DAO;

import com.assessment.project.model.UserEntity;
import com.assessment.project.model.WordEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDAO")
public class UserDAOImpl extends AbstractDAO<Integer, UserEntity> implements UserDAO {
    @Override
    public UserEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("id",id));
        return (UserEntity)criteria.uniqueResult();
    }

    @Override
    public UserEntity findByLogin(String login) {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
        return (UserEntity)criteria.add(Restrictions.eq("login",login)).uniqueResult();
    }

    @Override
    public void saveUser(UserEntity user) {
        getSession().save(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        getSession().update(user);
    }

    @Override
    public void deleteUser(int id) {
        Query query = getSession().createSQLQuery("delete from user where ID = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    @Override
    public List<UserEntity> findAllUsers() {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
        return (List<UserEntity>) criteria.list();
    }

    @Override
    public void deleteWord(UserEntity user, WordEntity song) {
       //TODO
    }

    @Override
    public void addWord(UserEntity user, WordEntity song) {
        //TODO
    }
}
