package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.Users;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        Users users = new Users();
        users.setUsername("admin");
        users.setPassword("$2a$10$4ufVw1.LyqG2S9qUEn/Ncu4v/R2tmwViB8yJhY6Jv8YkV4b8U2vEG");
        users.setEnabled(1);
        Role role = new Role();
        role.setUsers(users);
        role.setAuthority("ROLE_ADMIN");
        users.setRoles(role);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(users);
        entityManager.persist(role);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
