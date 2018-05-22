package com.assessment.project.service;

import com.assessment.project.DAO.RoleDAO;
import com.assessment.project.DAO.UserDAO;
import com.assessment.project.model.RoleEntity;
import com.assessment.project.model.UserEntity;
import com.assessment.project.model.WordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserDAO userDAO;
    @Autowired
    RoleDAO roleDAO;

    @Override
    public UserEntity findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public UserEntity findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    @Override
    public void saveUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RoleEntity status = roleDAO.findById(2);
        user.setRole(status);
        userDAO.saveUser(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
            userDAO.deleteUser(id);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userDAO.findAllUsers();
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
