package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.entities.Role;

public interface RoleDAO {
    void addRole(Role role);
    Role findRole(String role);
    void deleteRole(Role role);
}
