package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Алексей", "Петров", (byte) 25);
        outUser();
        userService.saveUser("Дмитрий", "Иванов", (byte) 35);
        outUser();
        userService.saveUser("Елена", "Сидорова", (byte) 20);
        outUser();
        userService.saveUser("Татьяна", "Смирнова", (byte) 23);
        outUser();

        System.out.println("-----------------------------------------------");

        ListIterator listIterator = userService.getAllUsers().listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next().toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

    public static void outUser () throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.getAllUsers().get(userService.countUsersTable());
        System.out.println("User с именем - " + user.getName() + " добавлен в базу данных");
    }
}
