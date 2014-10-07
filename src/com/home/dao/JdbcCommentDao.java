package com.home.dao;

import com.home.entity.Comment;
import com.home.entity.Heater;

import java.sql.*;
import java.util.ArrayList;

public class JdbcCommentDao {
    MainJdbcDao jdbcDao;
    public JdbcCommentDao() {
        this.jdbcDao = MainJdbcDao.getInstance();
    }

    public void create(Comment target) {
        String query = "INSERT INTO `comment` (`name`, `text`, `data`, `heater_id`) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = null;
        Connection connection = null;
        try {
            connection = jdbcDao.createConnection();
            pst = connection.prepareStatement(query);
            pst.setString((int)1 ,target.getName());
            pst.setString((int)2 ,target.getText());
            pst.setDate((int) 3, target.getData());
            pst.setString((int)4 ,target.getHeater_id());
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

    public void update(Comment target) {
        String query = "UPDATE `comment` SET `name`=?, `text`=?, `data`=? WHERE (`id`=?)";
        PreparedStatement pst = null;
        Connection connection = null;
        try {
            connection = jdbcDao.createConnection();
            pst = connection.prepareStatement(query);
            pst.setString((int)1 ,target.getName());
            pst.setString((int)2 ,target.getText());
            pst.setDate((int) 3, target.getData());
            pst.setString((int)4 ,target.getId());
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

    public void remove(Comment target) {
        String query = "DELETE FROM `comment` WHERE (`id`=?)";
        PreparedStatement pst = null;
        Connection connection = null;
        try {
            connection = jdbcDao.createConnection();
            pst = connection.prepareStatement(query);
            pst.setString((int)1 ,target.getId());
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

    public Comment[] find(String heater_id) {
        ArrayList<Comment> heaters = new ArrayList<>();

        Connection connection = null;
        ResultSet rs = null;
        Statement st = null;
        Comment temp = null;
        JdbcImagesDao imagesDao = new JdbcImagesDao();

        connection = jdbcDao.createConnection();
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM `comment` WHERE `comment`.heater_id = '"+heater_id+"'");

            while (rs.next()) {
                temp = new Comment();
                temp.setId(rs.getString("id"));
                temp.setName(rs.getString("name"));
                temp.setData(rs.getDate("data"));
                temp.setText(rs.getString("text"));
                temp.setHeater_id(rs.getString("heater_id"));
                heaters.add(temp);
            }
        } catch (SQLException e) {
            System.err.println("SQL Query error!");
        } finally {
            try {
                connection.close();
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Comment[] h = new Comment[heaters.size()];
        return heaters.toArray(h);
    }
}
