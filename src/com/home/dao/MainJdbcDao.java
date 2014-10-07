package com.home.dao;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MainJdbcDao {
    private static BasicDataSource basicDataSource = null;
    private static MainJdbcDao mainJdbcDao = null;

    public MainJdbcDao() {
        this.loadProperties();
    }

    public static MainJdbcDao getInstance() {
        if (mainJdbcDao == null) {
            mainJdbcDao = new MainJdbcDao();
        }
        return mainJdbcDao;
    }

    public void loadProperties() {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/db.properties");

        Properties props = new Properties();
        try {
            props.load(resourceAsStream);
        } catch (Exception e) {
            System.err.println("Error load properties!");
        }

        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(props.getProperty("H2_DRIVER_CLASS"));
        basicDataSource.setUrl(props.getProperty("H2_URL_START") + props.getProperty("H2_PATH") + props.getProperty("H2_URL_END"));
        basicDataSource.setUsername(props.getProperty("H2_USERNAME"));
        basicDataSource.setPassword(props.getProperty("H2_PASSWORD"));
    }

    public Connection createConnection() {
        try {
            return basicDataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("Error get connection!");
        }
        return null;
    }

    public void initBD() {
        try {
            Statement statement = createConnection().createStatement();
            statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0");
            statement.executeUpdate("DROP TABLE IF EXISTS `heater`");
            statement.executeUpdate("CREATE TABLE `heater` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(255) DEFAULT NULL,\n" +
                    "  `type` varchar(255) DEFAULT NULL,\n" +
                    "  `producer` varchar(255) DEFAULT NULL,\n" +
                    "  `covering` varchar(255) DEFAULT NULL,\n" +
                    "  `power` varchar(255) DEFAULT NULL,\n" +
                    "  `protection` varchar(255) DEFAULT NULL,\n" +
                    "  `price` decimal(10,0) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB");
            statement.executeUpdate("DROP TABLE IF EXISTS `images`");
            statement.executeUpdate("CREATE TABLE `images` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(255) DEFAULT NULL,\n" +
                    "  `url` varchar(255) DEFAULT NULL,\n" +
                    "  `heater_id` int(11) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  CONSTRAINT `heater_id` FOREIGN KEY (`heater_id`) REFERENCES `heater` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
                    ") ENGINE=InnoDB");
            statement.executeUpdate("DROP TABLE IF EXISTS `comment`");
            statement.executeUpdate("CREATE TABLE `comment` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(255) DEFAULT NULL,\n" +
                    "  `text` varchar(255) DEFAULT NULL,\n" +
                    "  `data` date DEFAULT NULL,\n" +
                    "  `heater_id` int(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  CONSTRAINT `heater` FOREIGN KEY (`heater_id`) REFERENCES `heater` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION\n" +
                    ") ENGINE=InnoDB");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}


