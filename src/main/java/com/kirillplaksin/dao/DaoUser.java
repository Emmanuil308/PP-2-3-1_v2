package com.kirillplaksin.dao;

import com.kirillplaksin.entity.User;

import java.util.List;

public interface DaoUser {

    public List<User> getAllUser();

    public User getUserById(int id);

    public void saveUser(User user);

    public void removeUserById(int id);
}
