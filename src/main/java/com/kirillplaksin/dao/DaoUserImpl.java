package com.kirillplaksin.dao;

import com.kirillplaksin.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;


import javax.persistence.*;

import java.util.List;

@Repository
public class DaoUserImpl implements DaoUser{

    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext()
    private EntityManager em;

    @Autowired
    public DaoUserImpl(LocalContainerEntityManagerFactoryBean emf) {
        this.entityManagerFactory = emf.getObject();
    }

    public List<User> getAllUser() {

        List<User> userList;
        try {
            em = entityManagerFactory.createEntityManager();
            TypedQuery<User> userTypedQuery = em.createQuery("from User", User.class);
            userList = userTypedQuery.getResultList();

            return userList;

        } catch (Exception e) {
            e.getStackTrace();
            return null;

        } finally {
            if (em.isOpen()) { em.close(); }
        }
    }

    public User getUserById(int id) {
        try {
            em = entityManagerFactory.createEntityManager();
            return em.find(User.class, id);

        } catch (Exception e) {
            e.getStackTrace();
            return null;
        } finally {
            if (em.isOpen()) { em.close(); }
        }
    }

    /*При открытии транзакции через аннотацию в сервисе не происходит сохранения нового User(а),
    но если привести EntityManager к Session через unwrap, то сохранение происходит через "транзакцию в аннотации".
    Похоже на какие-то настройки безопасности БД, но решения пока не нашёл*/
    public void saveUser(User user) {

        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            if (user.getId() == 0) {
                em.persist(user);
            } else {
                em.merge(user);
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            e.getStackTrace();
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
        }finally {
            if (em.isOpen()) { em.close(); }
        }
    }

    public void removeUserById(int id) {

        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();

            em.remove(em.find(User.class,id));

            em.getTransaction().commit();

        } catch (Exception e) {
            e.getStackTrace();
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) { em.close(); }
        }

    }
}
