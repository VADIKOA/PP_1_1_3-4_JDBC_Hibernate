package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl extends UserDaoJDBCImpl implements UserService {
    public void createUsersTable() {
        UserServiceImpl.super.createUsersTable();
    }

    public void dropUsersTable() {
        UserServiceImpl.super.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserServiceImpl.super.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserServiceImpl.super.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return UserServiceImpl.super.getAllUsers();
    }

    public void cleanUsersTable() {
        UserServiceImpl.super.cleanUsersTable();
    }
}
