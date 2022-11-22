package com.kirillplaksin.dao;

import com.kirillplaksin.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;


import javax.persistence.*;

import java.util.List;

@Repository
public class DaoUserImpl implements DaoUser {

    @PersistenceContext()
    private EntityManager em;

    public DaoUserImpl() {
    }

    public List<User> getAllUser() {

        TypedQuery<User> userTypedQuery = em.createQuery("from User", User.class);

        return userTypedQuery.getResultList();
    }

    public User getUserById(int id) {

        return em.find(User.class, id);
    }

    public void saveUser(User user) {

        if (user.getId() == 0) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    public void removeUserById(int id) {

        em.remove(em.find(User.class, id));


    }
}
