package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private Role role;

    public Role addRole(Users users) {
        role = new Role();
        role.setUsers(users);
        role.setAuthority(users.getRole());
        entityManager.persist(role);
        return role;
    }

    public Role findRoleByUsername (Users users) {
       return entityManager.createQuery("select role from Role role where role.users=:u",Role.class)
               .setParameter("u",users).getSingleResult();
    }

    public void deleteByUsername (Users users) {
        entityManager.remove(findRoleByUsername(users));
    }
}
