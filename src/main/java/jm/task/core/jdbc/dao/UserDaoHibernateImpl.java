package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoHibernateImpl.class.getName());
    private static final Session session = Util.getSessionFactory().openSession();
    private static Transaction transaction;

    @Override
    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS User (id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "firstname VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age TINYINT NOT NULL)";

        try {
            session.beginTransaction();
            session.createSQLQuery(sqlCreate).addEntity(User.class).executeUpdate();
            logger.info("Table create.");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE IF EXISTS User";
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery(sqlDrop).addEntity(User.class).executeUpdate();
            logger.info("Table drop.");
            session.getTransaction();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.info("Table not found.");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            logger.info("User save. [" + name + "," + lastName + "," + age + "]");
            session.getTransaction();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.info("Table not found.");
        }
    }


    @Override
    public void removeUserById(long id) {
        try {
            transaction = session.beginTransaction();

            session.createQuery("delete from User where id=?id", User.class);
//            session.remove(removeUserById(id));
//            session.remove(session.get(User.class, id));
            logger.info("User remove. Id = " + id);
            session.getTransaction();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.info("User with" + id + "not found");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
//            session.createQuery("from User ").list().forEach(User(setId,));
            userList = session.createQuery("select i from User i", User.class).getResultList();
            session.getTransaction();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.info("Table not found.");
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            transaction = session.beginTransaction();
            session.createQuery("delete from User ").executeUpdate();
            logger.info("Table cleaned.");
            session.getTransaction();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.info("Table not found");
        }
    }
}
