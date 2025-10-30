package upm.doo.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class MysqlTables {
    private static final Logger LOGGER = LogManager.getLogger(MysqlTables.class);
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "poodb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final String URL_H2 = "jdbc:h2:mem:test";

    private final Connection connection;

    public MysqlTables(String bd) {
        if ("h2".equals(bd)) {
            this.connection = this.createConnectionH2();
            LOGGER.info("H2 connection established successfully.");
        } else {
            LOGGER.warn("Development mode: dropping and recreating database.");
            this.dropDatabase();
            LOGGER.info("Database dropped successfully.");
            this.createDatabase();
            LOGGER.info("Database created successfully.");
            this.connection = this.createConnectionMySql();
            LOGGER.info("MySQL connection established successfully.");
        }
    }

    public static void main(String[] args) {
        MysqlTables mysqlTables = new MysqlTables("mysql");
        mysqlTables.createUserTable();
        mysqlTables.createArticleTable();
        mysqlTables.createShoppingCartTable();
        mysqlTables.createArticleItemTable();
        mysqlTables.createTag();
    }

    private Connection createConnectionMySql() {
        try {
            Class.forName(MysqlTables.DRIVER);
           return DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("Driver error: '" + MysqlTables.DRIVER + "'. " + e.getMessage());
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Connection error with '" + MysqlTables.URL + "'. " + e.getMessage());
        }
    }

    private Connection createConnectionH2() {
        try {
            return DriverManager.getConnection(URL_H2);
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Connection error with '" + URL_H2 + "'. " + e.getMessage());
        }
    }

    private void dropDatabase() {
        try (Connection connectionTmp = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connectionTmp.createStatement()) {
            statement.executeUpdate("DROP DATABASE IF EXISTS " + DATABASE + ";");
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Drop Database Error. "+e);
        }
    }

    private void createDatabase() {
        try (Connection connectionTmp = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connectionTmp.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE + ";");
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Create Database Error. " + e);
        }
    }

    public void executeUpdate(String sql) {
        LogManager.getLogger(this.getClass()).debug(() -> "Sql: " + sql);
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UnsupportedOperationException("SQL: " + sql + " ===>>> " + e);
        }
    }

    public void createUserTable() {
        final String sql = """
            CREATE TABLE IF NOT EXISTS `UserApp` (
              `id` INT NOT NULL AUTO_INCREMENT,
              `mobile` INT UNIQUE NOT NULL,
              `password` VARCHAR(20),
              `name` VARCHAR(20),
              `address` VARCHAR(50),
              `rol` VARCHAR(20),
              PRIMARY KEY (`id`)
            );
            """;
        this.executeUpdate(sql);
    }

    public void createArticleTable() {
        final String sql = """
            CREATE TABLE IF NOT EXISTS `Article` (
              `id` INT NOT NULL AUTO_INCREMENT,
              `barcode` VARCHAR(20) UNIQUE NOT NULL,
              `summary` VARCHAR(20),
              `price` DECIMAL(5,2),
              `registrationDate` DATE,
              `provider` VARCHAR(20),
              PRIMARY KEY (`id`)
            );
            """;
        this.executeUpdate(sql);
    }

    public void createShoppingCartTable() {
        final String sql = """
            CREATE TABLE IF NOT EXISTS `ShoppingCart` (
              `id` INT NOT NULL AUTO_INCREMENT,
              `user_id` INT NOT NULL,
              `creationDate` TIMESTAMP,
              PRIMARY KEY (`id`),
              FOREIGN KEY (`user_id`) REFERENCES `UserApp`(`id`)
            );
            """;
        this.executeUpdate(sql);
    }

    public void createArticleItemTable() {
        final String sql = """
            CREATE TABLE IF NOT EXISTS `ArticleItem` (
              `id` INT NOT NULL AUTO_INCREMENT,
              `shoppingCart_id` INT NOT NULL,
              `article_id` INT NOT NULL,
              `amount` INT,
              `discount` DECIMAL(4,2),
              PRIMARY KEY (`id`),
              FOREIGN KEY (`shoppingCart_id`) REFERENCES `ShoppingCart`(`id`) ON DELETE CASCADE,
              FOREIGN KEY (`article_id`) REFERENCES `Article`(`id`)
            );
            """;
        this.executeUpdate(sql);
    }

    public void createTag() {
        final String tag = """
            CREATE TABLE IF NOT EXISTS `Tag` (
              `id` INT NOT NULL AUTO_INCREMENT,
              `name` VARCHAR(20) UNIQUE NOT NULL,
              `description` VARCHAR(20),
              PRIMARY KEY (`id`)
            );
            """;

        final String tagArticle = """
            CREATE TABLE IF NOT EXISTS `Tag_Article` (
              `tag_id` INT NOT NULL,
              `article_id` INT NOT NULL,
              PRIMARY KEY (`tag_id`, `article_id`),
              FOREIGN KEY (`tag_id`) REFERENCES `Tag`(`id`),
              FOREIGN KEY (`article_id`) REFERENCES `Article`(`id`)
            );
            """;
        this.executeUpdate(tag);
        this.executeUpdate(tagArticle);
    }

}
