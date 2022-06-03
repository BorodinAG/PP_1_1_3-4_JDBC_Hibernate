package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());
    private static final Connection connection = Util.getConnection();

    public void createUsersTable() {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement
                    ("CREATE TABLE IF NOT EXISTS Users(id int PRIMARY KEY AUTO_INCREMENT," +
                            "FirstName varchar(255), LastName varchar(255), Age int)");
            preparedStatement.executeUpdate();
            logger.info("Table create.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS Users");
            preparedStatement.executeUpdate();
            logger.info("Table drop.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveUser(String firstname, String lastName, byte age) {

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("INSERT INTO Users (firstname,lastName,age) VALUES(?,?,?)");
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            logger.info("User save. [" + firstname + "," + lastName + "," + age + "]");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            logger.info("User remove. Id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new User(resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getByte("Age")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("Get all users good.");
        return list;
    }

    public void cleanUsersTable() {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("TRUNCATE TABLE Users");
            preparedStatement.executeUpdate();
            logger.info("Table cleaned.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
