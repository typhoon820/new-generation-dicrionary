package com.assessment.project.DAO;

import com.assessment.project.model.RoleEntity;
import com.assessment.project.model.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Никита on 25.05.2017.
 */
@Repository("RoleDAO")
public class RoleDAOImpl extends AbstractDAO<Integer, RoleEntity> implements RoleDAO {
    @Override
    public RoleEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(RoleEntity.class);
        criteria.add(Restrictions.eq("id",id));
        return (RoleEntity) criteria.uniqueResult();
    }

    @Override
    public void saveRole(RoleEntity role) {
        getSession().save(role);
    }

    @Override
    public void deleteRole(int id) {
        Query query = getSession().createSQLQuery("delete from role where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();
    }

    @Override
    public List<RoleEntity> findAllRoles() {
        Criteria criteria = getSession().createCriteria(RoleEntity.class);
        return (List<RoleEntity>) criteria.list();
    }
}
