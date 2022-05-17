package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getListOfUsers() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findByUserName(String username) {
        return entityManager.createQuery("select user from User user where user.username=:username", User.class)
                .setParameter("username", username).getSingleResult();
    }


}
