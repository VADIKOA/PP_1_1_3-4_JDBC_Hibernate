package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createSQLQuery("CREATE TABLE IF NOT EXISTS `javapp`.`users` (`id` INT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL," +
                "`age` TINYINT(3) NOT NULL, PRIMARY KEY (`id`)," +
                "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)").executeUpdate();

        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() throws SQLException {
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createSQLQuery("DROP TABLE `javapp`.`users`").executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(new User(name, lastName, age));

        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);
        session.remove(user);

        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<User> userList = session.createQuery("SELECT u FROM User u", User.class).getResultList();

        transaction.commit();
        session.close();

        return userList;
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("DELETE FROM User").executeUpdate();

        transaction.commit();
        session.close();
    }

    public int countUsersTable() throws SQLException {
        int count;
        Session session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        count = (int) ((long)session.createQuery("SELECT count(*) FROM User").getSingleResult());

        transaction.commit();
        session.close();
        return --count;
    }
}
