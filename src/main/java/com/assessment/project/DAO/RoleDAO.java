package com.assessment.project.DAO;

import com.assessment.project.model.RoleEntity;
import com.assessment.project.model.ThemeEntity;
import com.assessment.project.model.TranslationEntity;

import java.util.List;

/**
 * Created by Никита on 25.05.2017.
 */
public interface RoleDAO {
    RoleEntity findById(int id);
    void saveRole (RoleEntity role);
    void deleteRole(int id);
    List<RoleEntity> findAllRoles();
}
