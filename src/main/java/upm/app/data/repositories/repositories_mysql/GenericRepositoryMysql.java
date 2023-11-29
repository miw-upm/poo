package upm.app.data.repositories.repositories_mysql;

import org.apache.logging.log4j.LogManager;
import upm.app.data.repositories.GenericRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepositoryMysql<T> implements GenericRepository<T> {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/poo";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private final Statement statement;

    protected GenericRepositoryMysql() {
        this.statement = this.createStatement();
    }

    private Statement createStatement() {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection.createStatement();
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("Driver error: '" + DRIVER + "'. " + e.getMessage());
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Connection error with '" + URL + "'. " + e.getMessage());
        }
    }

    protected void executeUpdate(String sql) {
        LogManager.getLogger(this.getClass()).debug(() -> "Sql: " + sql);
        try {
            this.statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new UnsupportedOperationException("SQL: " + sql + " ===>>> " + e);
        }
    }

    protected List<T> executeQuery(String sql) {
        LogManager.getLogger(this.getClass()).debug(() -> ("Sql: " + sql));
        List<T> entities = new ArrayList<>();
        try {
            ResultSet resultSet = this.statement.executeQuery(sql);
            while (resultSet.next()) {
                entities.add(this.retriever(resultSet));
            }
            return entities;
        } catch (SQLException e) {
            throw new UnsupportedOperationException("SQL: " + sql + " ===>>> " + e);
        }
    }

    protected abstract T retriever(ResultSet resulSet);



}
