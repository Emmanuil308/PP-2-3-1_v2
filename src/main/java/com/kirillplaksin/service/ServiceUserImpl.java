package com.kirillplaksin.service;

import com.kirillplaksin.dao.DaoUser;
import com.kirillplaksin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceUserImpl implements ServiceUser {

    private DaoUser daoUser;

    @Autowired
    public ServiceUserImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @Transactional
    public List<User> getAllUser() {
        return daoUser.getAllUser();
    }

    @Transactional
    public User getUserById(int id) {
        return daoUser.getUserById(id);
    }

    @Transactional
    public void saveUser(User user) {
        daoUser.saveUser(user);
    }

    @Transactional
    public void removeUserById(int id) {

        daoUser.removeUserById(id);
    }
}
