package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private Role role;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findRole(String role) {
        return entityManager.createQuery("select role from Role role where role.authority=:role", Role.class)
                .setParameter("role", role).getSingleResult();

    }

    @Override
    public void deleteRole(Role role) {
        entityManager.remove(role);
    }
}
