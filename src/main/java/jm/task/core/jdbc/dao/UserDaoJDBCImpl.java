package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Statement statement = Util.getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS `javapp`.`users` (`id` INT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL," +
                "`age` TINYINT(3) NOT NULL, PRIMARY KEY (`id`), " +
                "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)");
        statement.close();
    }

    public void dropUsersTable() throws SQLException {
        Statement statement = Util.getConnection().createStatement();
        statement.executeUpdate("DROP TABLE `javapp`.`users`");
        statement.close();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        final String INSERT_NEW = "INSERT INTO users (name, lastName, age) VALUES (?,?,?)";
        PreparedStatement preparedStatement = Util.getConnection().prepareStatement(INSERT_NEW);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);
        preparedStatement.execute();
        preparedStatement.close();

    }

    public void removeUserById(long id) throws SQLException {
        final  String REMOVE = "DELETE FROM users WHERE id = ?";
        PreparedStatement preparedStatement = Util.getConnection().prepareStatement(REMOVE);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<User> getAllUsers() throws SQLException {
        final String GET_ALL = "SELECT * FROM users";
        PreparedStatement preparedStatement = Util.getConnection().prepareStatement(GET_ALL);
        ResultSet res = preparedStatement.executeQuery();
        List<User> listUser = new ArrayList<>();
        while (res.next()) {
            User user = new User();
            user.setId(res.getLong("id"));
            user.setName(res.getString("name"));
            user.setLastName(res.getString("lastName"));
            user.setAge(res.getByte("age"));
            listUser.add(user);
        }
        preparedStatement.close();
        return listUser;
    }

    public void cleanUsersTable() throws SQLException {
        final  String REMOVE_ALL = "DELETE FROM users";
        PreparedStatement preparedStatement = Util.getConnection().prepareStatement(REMOVE_ALL);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public int countUsersTable() throws SQLException {
        int count;
        final  String COUNT = "SELECT count(*) FROM users";
        Statement statement = Util.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(COUNT);
        resultSet.next();
        count = resultSet.getInt(1);
        statement.close();
        return --count;
    }
}
