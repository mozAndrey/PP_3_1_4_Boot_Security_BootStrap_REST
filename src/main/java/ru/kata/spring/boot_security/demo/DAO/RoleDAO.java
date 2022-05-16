package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.Users;

public interface RoleDAO {
    Role addRole(Users users);

    Role findRoleByUsername (Users users);

    void deleteByUsername (Users users);
}
