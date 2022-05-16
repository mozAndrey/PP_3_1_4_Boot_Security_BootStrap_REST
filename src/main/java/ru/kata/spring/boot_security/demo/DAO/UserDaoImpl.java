package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Users> getListOfUsers() {
        return entityManager.createQuery("select role from Users role", Users.class).getResultList();
    }

    @Override
    public void addUser(Users users) {
        entityManager.persist(users);
    }

    @Override
    public Users findById(String username) {
        return entityManager.createQuery("select users from Users users where users.username=:username",Users.class)
                .setParameter("username",username)
                .getSingleResult();
    }

    @Override
    public void deleteById(String username) {
        entityManager.remove(findById(username));
    }

    @Override
    public void update(Users users) {
        users.setEnabled(1);
        entityManager.merge(users);
    }


}
