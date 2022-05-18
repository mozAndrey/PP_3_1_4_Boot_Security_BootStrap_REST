package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

    @Override
    public Role findRole(String role) {
        return roleDAO.findRole(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleDAO.deleteRole(role);
    }

    @Override
    public Set<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }
}
