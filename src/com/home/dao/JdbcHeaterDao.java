package com.home.dao;

import com.home.entity.Heater;
import com.home.query.SearchHeater;

import java.sql.*;
import java.util.ArrayList;

public class JdbcHeaterDao {
    MainJdbcDao jdbcDao;

    public JdbcHeaterDao() {
        this.jdbcDao = MainJdbcDao.getInstance();
    }

    public void create(Heater target) {
        String query = "INSERT INTO `heater` (`name`, `type`, `producer`, `covering`, `power`, `protection`, `price`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = null;
        Connection connection = null;
        try {
            connection = jdbcDao.createConnection();
            pst = connection.prepareStatement(query);
            pst.setString((int)1 ,target.getName());
            pst.setString((int)2 ,target.getType());
            pst.setString((int)3 ,target.getProducer());
            pst.setString((int)4 ,target.getCovering());
            pst.setString((int)5 ,target.getPower());
            pst.setString((int)6 ,target.getProtection());
            pst.setString((int)7 ,target.getPrice());
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

    public void update(Heater target) {
        String query = "UPDATE `heater` SET `name`=?, `type`=?, `producer`=?, `covering`=?, `power`=?, `protection`=?, `price`=? WHERE (`id`=?)";
        PreparedStatement pst = null;
        Connection connection = null;
        try {
            connection = jdbcDao.createConnection();
            pst = connection.prepareStatement(query);
            pst.setString((int)1 ,target.getName());
            pst.setString((int)2 ,target.getType());
            pst.setString((int)3 ,target.getProducer());
            pst.setString((int)4 ,target.getCovering());
            pst.setString((int)5 ,target.getPower());
            pst.setString((int)6 ,target.getProtection());
            pst.setString((int)7 ,target.getPrice());
            pst.setString((int)8 ,target.getId());
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

    public void remove(Heater target) {
        String query = "DELETE FROM `heater` WHERE (`id`=?)";
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

    private String generateQuery(SearchHeater search) {
        ArrayList<String> parameter = new ArrayList<>();
        StringBuffer query = new StringBuffer("SELECT * FROM `heater` WHERE ");

        if(search.getId() != null) {
            parameter.add("heater.`id` = '"+search.getId()+"'");
        }

        if(search.getName() != null) {
            parameter.add("heater.`name` = '"+search.getName()+"'");
        }


        if(search.getType() != null) {
            parameter.add("heater.`type` = '"+search.getType()+"'");
        }

        if(search.getProducer() != null) {
            parameter.add("heater.producer = '"+search.getProducer()+"'");
        }

        if(search.getProtection() != null) {
            parameter.add("heater.protection = '"+search.getProtection()+"'");
        }

        if(search.getMinPrice() != null) {
            parameter.add("heater.price >= '" + search.getMinPrice() + "'");
        }

        if(search.getMaxPrice() != null) {
            parameter.add("heater.price <= '"+search.getMaxPrice()+"'");
        }

        if(search.getMinPower() != null) {
            parameter.add("heater.power >= '"+search.getMinPower()+"'");
        }

        if(search.getMaxPower() != null) {
            parameter.add("heater.power <= '"+search.getMaxPower()+"'");
        }

        if(search.getMinCovering() != null) {
            parameter.add("heater.covering >= '"+search.getMinCovering()+"'");
        }

        if(search.getMaxCovering() != null) {
            parameter.add("heater.covering <= '"+search.getMaxCovering()+"'");
        }

        if(parameter.size() > 0) {
            query.append(parameter.get(0));
            for(int i = 1; i<parameter.size(); i++) {
                query.append(" AND ");
                query.append(parameter.get(i));
            }
        } else {
            query.append("1");
        }

        if(search.getOffset() != null) {
            query.append(" LIMIT "+search.getOffset());
        } else {
            query.append(" LIMIT 0");
        }

        if(search.getAmount() != null) {
            query.append(", "+search.getAmount());
        } else {
            query.append(", 5");
        }
        return query.toString();
    }

    public Heater[] find(SearchHeater search) {
        ArrayList<Heater> heaters = new ArrayList<>();

        Connection connection = null;
        ResultSet rs = null;
        Statement st = null;
        Heater temp = null;
        JdbcImagesDao imagesDao = new JdbcImagesDao();

        connection = jdbcDao.createConnection();
        try {
            st = connection.createStatement();
            rs = st.executeQuery(generateQuery(search));

            while (rs.next()) {
                temp = new Heater();
                temp.setId(rs.getString("id"));
                temp.setName(rs.getString("name"));
                temp.setType(rs.getString("type"));
                temp.setProducer(rs.getString("producer"));
                temp.setCovering(rs.getString("covering"));
                temp.setPower(rs.getString("power"));
                temp.setProtection(rs.getString("protection"));
                temp.setPrice(rs.getString("price"));
                temp.setImageses(imagesDao.findByHeaterId(temp.getId()));
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

        Heater[] h = new Heater[heaters.size()];
        return heaters.toArray(h);
    }

    public Heater getLast() {
        Connection connection = null;
        ResultSet rs = null;
        Statement st = null;
        Heater temp = null;

        JdbcImagesDao imagesDao = new JdbcImagesDao();

        connection = jdbcDao.createConnection();
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT heater.id FROM `heater` WHERE 1 ORDER BY heater.id DESC LIMIT 1");

            if(rs.next()) {
                temp = new Heater();
                temp.setId(rs.getString("id"));
                temp.setName(rs.getString("name"));
                temp.setType(rs.getString("type"));
                temp.setProducer(rs.getString("producer"));
                temp.setCovering(rs.getString("covering"));
                temp.setPower(rs.getString("power"));
                temp.setProtection(rs.getString("protection"));
                temp.setPrice(rs.getString("price"));
                temp.setImageses(imagesDao.findByHeaterId(temp.getId()));
            } else {
                return null;
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

        return temp;
    }
}
