package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;

@Component
@Transactional
public class Init {
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public Init(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @PostConstruct
    @Transactional
    public void postConstruct() {
        User users = new User();
        users.setUsername("admin");
        users.setPassword("$2a$10$Lpph9iqmvADIN40HpKCZteeNE0F1sObg9w/DGsdF7DOpwZbMxHxoa");
        Role role = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        users.setRoles(Collections.singleton(role));
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(users);
        entityManager.persist(role);
        entityManager.persist(role2);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
