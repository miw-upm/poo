package upm.app.data.repositories.repositories_mysql;

import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryMysql {
    public static final String DATABASE = "poo";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "root";
    public static final String PASSWORD = "";


    public void dropAndCreateDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("USE mysql;");
            statement.executeUpdate("DROP DATABASE IF EXISTS " + DATABASE + ";");
            statement.executeUpdate("CREATE DATABASE " + DATABASE + ";");
        } catch (SQLException e) {
            LogManager.getLogger(this.getClass()).debug(e);
        }
    }
}
