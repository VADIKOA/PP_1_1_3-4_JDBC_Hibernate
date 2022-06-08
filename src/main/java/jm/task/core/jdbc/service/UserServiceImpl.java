package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl extends UserDaoJDBCImpl implements UserService {
    public void createUsersTable() throws SQLException {
        UserServiceImpl.super.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        UserServiceImpl.super.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        UserServiceImpl.super.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        UserServiceImpl.super.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return UserServiceImpl.super.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        UserServiceImpl.super.cleanUsersTable();
    }
}
