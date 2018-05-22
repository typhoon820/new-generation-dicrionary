package com.assessment.project.service;

import com.assessment.project.model.RoleEntity;

import java.util.List;

/**
 * Created by Никита on 25.05.2017.
 */
public interface RoleService {
    RoleEntity findById(int id);
    void saveRole (RoleEntity role);
    void deleteRole(int id);
    List<RoleEntity> findAllRoles();
}
