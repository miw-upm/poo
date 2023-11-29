package upm.app.data.repositories.repositories_mysql;

import org.apache.logging.log4j.LogManager;
import upm.app.data.repositories.GenericRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericRepositoryMysql<T> implements GenericRepository<T> {
    private final Statement statement;

    protected GenericRepositoryMysql() {
        this.statement = this.createStatement();
    }

    private Statement createStatement() {
        try {
            Class.forName(RepositoryMysql.DRIVER);
            Connection connection = DriverManager.getConnection(
                    RepositoryMysql.URL + RepositoryMysql.DATABASE, RepositoryMysql.USER, RepositoryMysql.PASSWORD);
            return connection.createStatement();
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("Driver error: '" + RepositoryMysql.DRIVER + "'. " + e.getMessage());
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Connection error with '" + RepositoryMysql.URL + "'. " + e.getMessage());
        }
    }

    protected Integer executeInsertGeneratedKey(String sql){
        LogManager.getLogger(this.getClass()).debug(() -> "Sql: " + sql);
        try {
            int rows = this.statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            if (rows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            throw new UnsupportedOperationException("SQL: " + sql + " ===>>> No se ha podido generar la id");
        } catch (SQLException e) {
            throw new UnsupportedOperationException("SQL: " + sql + " ===>>> " + e);
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

    protected ResultSet executeQuery(String sql) {
        LogManager.getLogger(this.getClass()).debug(() -> ("Sql: " + sql));
        try {
            return this.statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new UnsupportedOperationException("SQL: " + sql + " ===>>> " + e);
        }
    }

    protected List<T> executeQueryConvert(String sql) {
        List<T> entities = new ArrayList<>();
        ResultSet resultSet = this.executeQuery(sql);
        try {
            while (resultSet.next()) {
                entities.add(this.convertToEntity(resultSet));
            }
            return entities;
        } catch (SQLException e) {
            throw new UnsupportedOperationException("SQL: " + sql + " ===>>> " + e);
        }
    }

    protected abstract T convertToEntity(ResultSet resulSet);



}
