package com.github.beisser.service;

import com.github.beisser.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nico on 08.08.2016.
 */
@ApplicationScoped
public class DbService {

    // variable to store the dataSource / tomcat connection pool
    private DataSource dataSource;
    // name of the connection pool
    private String jndiName = "java:comp/env/jdbc/users";

    // method to get all users from the database
    public List<User> findAll() throws Exception {

        // create empty array
        List<User> users = new ArrayList<User>();

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
                java.sql.Date birthdaySql = resultSet.getDate("birthday");
                Date birthday = new Date(birthdaySql.getTime());
                String street = resultSet.getString("street");
                int plz = resultSet.getInt("plz");
                String city = resultSet.getString("city");

                User currentUser = new User(id, firstName, lastName,
                        email,birthday,street,plz,city);

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
            String sql = "insert into users (first_name, last_name, email, birthday, street, plz, city) values (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            // set params
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setDate(4,new java.sql.Date(user.getBirthday().getTime()));
            statement.setString(5,user.getStreet());
            statement.setInt(6,user.getPlz());
            statement.setString(7,user.getCity());

            statement.execute();
        }
        finally {
            // clean up
            _close(connection,statement,null);
        }
    }

    public User findById(int userId) throws Exception{
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
                java.sql.Date birthdaySql = resultSet.getDate("birthday");
                Date birthday = new Date(birthdaySql.getTime());
                String street = resultSet.getString("street");
                int plz = resultSet.getInt("plz");
                String city = resultSet.getString("city");

                fetchedUser = new User(id, firstName, lastName,
                        email,birthday,street,plz,city);
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
                    + " set first_name=?, last_name=?, email=?, birthday=?, street=?, plz=?, city=?"
                    + " where id=?";

            statement = connection.prepareStatement(sql);

            // set params
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setDate(4,new java.sql.Date(user.getBirthday().getTime()));
            statement.setString(5,user.getStreet());
            statement.setInt(6,user.getPlz());
            statement.setString(7,user.getCity());
            statement.setInt(8, user.getId());

            statement.execute();
        }
        finally {
            _close(connection, statement,null);
        }
    }

    public void delete(int userId) throws Exception {

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
    // private DbService() throws Exception{
    public DbService() throws Exception{
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
