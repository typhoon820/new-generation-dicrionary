package com.assessment.project.service;

import com.assessment.project.DAO.RoleDAO;
import com.assessment.project.model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("RoleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;


    @Override
    public RoleEntity findById(int id) {
        return roleDAO.findById(id);
    }

    @Override
    public void saveRole(RoleEntity role) {
        roleDAO.saveRole(role);
    }

    @Override
    public void deleteRole(int id) {
        roleDAO.deleteRole(id);
    }

    @Override
    public List<RoleEntity> findAllRoles() {
        return roleDAO.findAllRoles();
    }
}
