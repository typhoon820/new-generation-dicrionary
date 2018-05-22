package com.assessment.project.service;

import com.assessment.project.model.UserEntity;
import com.assessment.project.model.WordEntity;

import java.util.List;

/**
 * Created by Никита on 25.05.2017.
 */
public interface UserService {
    UserEntity findById(int id);
    UserEntity findByLogin(String login);
    void saveUser(UserEntity user);
    void updateUser(UserEntity user);
    void deleteUser(int id);
    List<UserEntity> findAllUsers();
    void deleteWord(UserEntity user, WordEntity song);
    void addWord(UserEntity user, WordEntity song);
}
