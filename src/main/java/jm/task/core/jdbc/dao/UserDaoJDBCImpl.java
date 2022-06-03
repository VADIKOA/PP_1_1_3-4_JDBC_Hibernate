package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Util util = new Util();

    public void createUsersTable() {

        try(Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE `javapp`.`users` (`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL," +
                    "`age` TINYINT(3) NOT NULL, PRIMARY KEY (`id`), " +
                    "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)");
        } catch (SQLSyntaxErrorException e) {
            //ignore
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE `javapp`.`users`");
        } catch (SQLSyntaxErrorException e) {
            //ignore
        }
          catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String INSERT_NEW = "INSERT INTO users (name, lastName, age) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(INSERT_NEW)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        final  String REMOVE = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(REMOVE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        final String GET_ALL = "SELECT * FROM users";
        List listUser = null;
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(GET_ALL)) {
            ResultSet res = preparedStatement.executeQuery();
            listUser = new ArrayList<User>();
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastName"));
                user.setAge(res.getByte("age"));
                listUser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUser;
    }

    public void cleanUsersTable() {
        final  String REMOVE_ALL = "DELETE FROM users";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(REMOVE_ALL)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int countUsersTable() {
        int count = 0;
        final  String COUNT = "SELECT count(*) FROM users";
        try (Statement statement = util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(COUNT);
            resultSet.next();
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return --count;
    }
}
