package com.home.dao;

import com.home.entity.Images;
import com.home.entity.User;

import java.sql.*;
import java.util.ArrayList;

public class JdbcUserDao {
    private MainJdbcDao jdbcDao;

    public JdbcUserDao() {
        jdbcDao = new MainJdbcDao();
    }

    public void create(User target) {
        String query = "INSERT INTO `user` (`login`, `password`, `name`) VALUES (?, ?, ?)";
        PreparedStatement pst = null;
        Connection connection = null;
        try {
            connection = jdbcDao.createConnection();
            pst = connection.prepareStatement(query);
            pst.setString((int)1 ,target.getLogin());
            pst.setString((int)2 ,target.getPassword());
            pst.setString((int)3 ,target.getName());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {}
        }
    }

    public void update(User target) {
        String query = "UPDATE `user` SET `login`=?, `password`=?, `name`=? WHERE (`id`=?)";
        PreparedStatement pst = null;
        Connection connection = null;
        try {
            connection = jdbcDao.createConnection();
            pst = connection.prepareStatement(query);
            pst.setString((int)1 ,target.getLogin());
            pst.setString((int)2 ,target.getPassword());
            pst.setString((int)3 ,target.getName());
            pst.setInt((int) 4, target.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {}
        }
    }

    public void remove(User target) {
        String query = "DELETE FROM `user` WHERE (`id`=?)";
        PreparedStatement pst = null;
        Connection connection = null;
        try {
            connection = jdbcDao.createConnection();
            pst = connection.prepareStatement(query);
            pst.setInt((int) 1, target.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {}
        }
    }

    public User auth(String login, String password) {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = null;

        connection = jdbcDao.createConnection();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM `user` WHERE `user`.login = ? AND `user`.`password` = ?");
            preparedStatement.setString((int) 1, login);
            preparedStatement.setString((int) 2, password);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return user;
    }
}
