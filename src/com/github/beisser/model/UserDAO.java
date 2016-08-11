package com.github.beisser.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico on 08.08.2016.
 */
public class UserDAO {

    // variable to store the current singleton instance
    private static UserDAO instance;
    // variable to store the dataSource / tomcat connection pool
    private DataSource dataSource;
    // name of the connection pool
    private String jndiName = "java:comp/env/jdbc/users";

    // implementing Singleton Pattern
    // this method gives a handle to the only instance of the class
    public static UserDAO getInstance() throws Exception {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    // method to get all users from the database
    public List<User> getUsers() throws Exception {

        // create empty array
        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // get a connection from the connection pool / dataSource
            connection = _getConnection();

            // sql code
            String sql = "select * from users order by last_name";

            // create and execute the sql code
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            // process result set from the database
            while (resultSet.next()) {

                // retrieve data from result set row
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                User currentUser = new User(id,firstName,lastName,email);

                // add it to the list of users
                users.add(currentUser);
            }

            return users;
        }
        finally {
            _close (connection, statement, resultSet);
        }
    }

    public void addUser(User user) throws Exception {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // get a connection from the connection pool
            connection = _getConnection();

            // prepared sql statement
            String sql = "insert into users (first_name, last_name, email) values (?, ?, ?)";
            statement = connection.prepareStatement(sql);

            // set params
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());

            statement.execute();
        }
        finally {
            // clean up
            _close(connection,statement,null);
        }
    }

    public User getUser(int userId) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = _getConnection();

            String sql = "select * from users where id=?";

            statement = connection.prepareStatement(sql);

            // set params
            statement.setInt(1, userId);

            resultSet = statement.executeQuery();

            User fetchedUser = null;

            // retrieve data from result set row
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");

                fetchedUser = new User(id, firstName, lastName,
                        email);
            }
            else {
                throw new Exception("Unable to find user with id: " + userId);
            }

            return fetchedUser;
        }
        finally {
            _close(connection, statement, resultSet);
        }
    }

    public void updateUser(User user) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = _getConnection();

            String sql = "update users "
                    + " set first_name=?, last_name=?, email=?"
                    + " where id=?";

            statement = connection.prepareStatement(sql);

            // set params
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getId());

            statement.execute();
        }
        finally {
            _close(connection, statement,null);
        }
    }

    public void deleteUser(int userId) throws Exception {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = _getConnection();

            String sql = "delete from users where id=?";

            statement = connection.prepareStatement(sql);

            // set params
            statement.setInt(1, userId);

            statement.execute();
        }
        finally {
            _close(connection, statement,null);
        }
    }

    // making the constructor private to force using getInstance to get the instance
    private UserDAO() throws Exception{
        // get the dataSource / Connection Pool and assign it the variable
        dataSource = _getDataSource();
    }

    // method to access the dataSource provided by tomcat using jndi
    private DataSource _getDataSource() throws NamingException {
        Context context = new InitialContext();

        DataSource theDataSource = (DataSource) context.lookup(jndiName);

        return theDataSource;
    }

    // getting one connection from the connection pool / dataSource
    private Connection _getConnection() throws Exception {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    // clean up method
    private void _close(Connection connection, Statement statement, ResultSet resultSet) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
