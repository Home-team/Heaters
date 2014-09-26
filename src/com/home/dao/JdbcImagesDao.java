package com.home.dao;

import com.home.entity.Images;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class JdbcImagesDao {
    MainJdbcDao jdbcDao;

    public JdbcImagesDao() {
        this.jdbcDao = MainJdbcDao.getInstance();
    }

    public void create(Images target) {
        String query = "INSERT INTO `images` (`name`, `url`, `heater_id`) VALUES (?, ?, ?)";
        PreparedStatement pst = null;
        Connection connection = null;
        try {
            connection = jdbcDao.createConnection();
            pst = connection.prepareStatement(query);
            pst.setString((int)1 ,target.getName());
            pst.setString((int)2 ,target.getUrl());
            pst.setString((int)3 ,target.getHeater_id());
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

    public void update(Images target) {
        String query = "UPDATE `images` SET `name`=?, `url`=?, `heater_id`=? WHERE (`id`=?)";
        PreparedStatement pst = null;
        Connection connection = null;
        try {
            connection = jdbcDao.createConnection();
            pst = connection.prepareStatement(query);
            pst.setString((int)1 ,target.getName());
            pst.setString((int)2 ,target.getUrl());
            pst.setString((int)3 ,target.getHeater_id());
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

    public void remove(Images target) {
        String query = "DELETE FROM `images` WHERE (`id`=?)";
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

    public Images[] findByHeaterId(String id) {
        String query = "SELECT * FROM `images` WHERE images.heater_id = '"+id+"'";
        Connection connection = null;
        ResultSet rs = null;
        Statement st = null;
        ArrayList<Images> retured = new ArrayList<>();
        Images temp = null;

        connection = jdbcDao.createConnection();
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);


            while (rs.next()) {
                temp = new Images();
                temp.setId(rs.getString("id"));
                temp.setName(rs.getString("name"));
                temp.setUrl(rs.getString("url"));
                temp.setHeater_id(rs.getString("heater_id"));
                retured.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Images[] imageses = new Images[retured.size()];
        return (Images[]) retured.toArray(imageses);
    }
}
