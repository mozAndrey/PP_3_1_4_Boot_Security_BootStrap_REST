package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class.getName());

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
        LOGGER.info("---------------"+users.getRoles()+"-----------");
        users.setEnabled(1);
        users.setRoles(users.getRoles());
        entityManager.merge(users);
    }


}
