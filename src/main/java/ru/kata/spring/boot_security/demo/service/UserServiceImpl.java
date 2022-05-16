package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDAOImpl;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.entities.Users;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private RoleDAOImpl roleDAOImpl;

    @Autowired
    public void setRoleDAO(RoleDAOImpl roleDAOImpl) {
        this.roleDAOImpl = roleDAOImpl;
    }

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<Users> getListUsers() {
        return userDAO.getListOfUsers();
    }

    @Override
    @Transactional
    public void addUser(Users users) {
        users.setEnabled(1);
        userDAO.addUser(users);
        users.setRoles(roleDAOImpl.addRole(users));
    }

    @Override
    public Users findById(String username) {
        return userDAO.findById(username);
    }

    @Override
    @Transactional
    public void deleteUserById(String username) {
        Users users = userDAO.findById(username);
        users.setRoles(null);
        roleDAOImpl.deleteByUsername(userDAO.findById(username));
        userDAO.deleteById(username);
    }

    @Override
    @Transactional
    public void update(Users users) {
        userDAO.update(users);
    }
}
