package upm.doo.sql;

import org.apache.logging.log4j.LogManager;

import java.sql.*;

public class MysqlTables {
    private static final String DATABASE = "poo";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public MysqlTables() {
        this.dropDatabase();  //Only develop
        this.createDatabase();
        this.connection = this.createConnection();
    }

    private void dropDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("USE mysql;");
            statement.executeUpdate("DROP DATABASE IF EXISTS " + DATABASE + ";");
        } catch (SQLException e) {
            LogManager.getLogger(this.getClass()).debug(e);
        }
    }

    private void createDatabase() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("USE mysql;");
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DATABASE + ";");
        } catch (SQLException e) {
            LogManager.getLogger(this.getClass()).debug(e);
        }
    }

    public Connection createConnection() {
        try {
            Class.forName(MysqlTables.DRIVER);
            return DriverManager.getConnection(
                    MysqlTables.URL + MysqlTables.DATABASE, MysqlTables.USER, MysqlTables.PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("Driver error: '" + MysqlTables.DRIVER + "'. " + e.getMessage());
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Connection error with '" + MysqlTables.URL + "'. " + e.getMessage());
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

    public void createUserTable(){
        this.executeUpdate("CREATE TABLE IF NOT EXISTS UserApp (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "mobile INT UNIQUE NOT NULL,password VARCHAR(20),name VARCHAR(20),address VARCHAR(50),rol VARCHAR(20))");
    }

    public void createArticleTable(){
        this.executeUpdate("CREATE TABLE IF NOT EXISTS Article (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "  barcode VARCHAR(20) UNIQUE NOT NULL, summary VARCHAR(20), price DECIMAL(5,2),registrationDate DATE," +
                "  provider VARCHAR(20))");
    }

    public void createShoppingCartTable(){
        this.executeUpdate("CREATE TABLE IF NOT EXISTS ShoppingCart (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "user_id INT NOT NULL, creationDate TIMESTAMP,FOREIGN KEY (user_id) REFERENCES UserApp(id))");
    }

    public void createArticleItemTableV1(){
        this.executeUpdate("CREATE TABLE IF NOT EXISTS ArticleItem (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "shoppingCart_id INT NOT NULL,article_id INT NOT NULL,amount INT,discount DECIMAL(4,2)," +
                "FOREIGN KEY (shoppingCart_id) REFERENCES ShoppingCart(id),FOREIGN KEY (article_id) REFERENCES Article(id))");
    }

    public void createArticleItemTableV2(){
        this.executeUpdate("CREATE TABLE IF NOT EXISTS ArticleItem (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "article_id INT NOT NULL,amount INT,discount DECIMAL(4,2)," +
                "FOREIGN KEY (article_id) REFERENCES Article(id))");

        this.executeUpdate("CREATE TABLE IF NOT EXISTS ShoppingCart_ArticleItem (" +
                "shoppingcart_id INT NOT NULL,articleItem_id INT NOT NULL," +
                "PRIMARY KEY (shoppingcart_id, articleItem_id)," +
                "FOREIGN KEY (shoppingcart_id) REFERENCES shoppingcart(id) ON DELETE CASCADE," +
                "FOREIGN KEY (articleItem_id) REFERENCES articleItem(id) ON DELETE CASCADE )");
    }

    public static void main(String[] args) {
        MysqlTables mysqlTables = new MysqlTables();
        mysqlTables.createUserTable();
        mysqlTables.createArticleTable();
        mysqlTables.createShoppingCartTable();
        mysqlTables.createArticleItemTableV2();
    }

}
