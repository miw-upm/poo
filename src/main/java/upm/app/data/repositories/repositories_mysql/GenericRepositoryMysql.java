package upm.app.data.repositories.repositories_mysql;

import org.apache.logging.log4j.LogManager;
import upm.app.data.repositories.GenericRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class GenericRepositoryMysql<T> implements GenericRepository<T> {

    private Connection connection;

    protected GenericRepositoryMysql() {
        this.prepareConnection();
    }

    private void prepareConnection() {
        try {
            Class.forName(RepositoryMysql.DRIVER);
            this.connection = DriverManager.getConnection(
                    RepositoryMysql.URL + RepositoryMysql.DATABASE, RepositoryMysql.USER, RepositoryMysql.PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("Driver error: '" + RepositoryMysql.DRIVER + "'. " + e.getMessage());
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Connection error with '" + RepositoryMysql.URL + "'. " + e.getMessage());
        }
    }

    protected Integer executeInsertGeneratedKey(String sql, Object... values) {
        LogManager.getLogger(this.getClass()).debug(() -> "Sql: " + sql);
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            this.assignValues(preparedStatement, values);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
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

    protected void executeUpdate(String sql, Object... values) {
        LogManager.getLogger(this.getClass()).debug(() -> "Sql: " + sql);
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            this.assignValues(preparedStatement, values);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UnsupportedOperationException("SQL: " + sql + " ===>>> " + e);
        }
    }

    protected <S> List<S> executeQueryFunctional(String sql, Function<ResultSet, S> convert, Object... values) {
        LogManager.getLogger(this.getClass()).debug(() -> ("Sql: " + sql));
        List<S> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            this.assignValues(preparedStatement, values);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entities.add(convert.apply(resultSet));
            }
        } catch (SQLException e) {
            throw new UnsupportedOperationException("SQL: " + sql + " ===>>> " + e);
        }
        return entities;
    }

    private void assignValues(PreparedStatement preparedStatement, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }

    protected List<T> executeQueryConvert(String sql, Object... values) {
        LogManager.getLogger(this.getClass()).debug(() -> ("Sql: " + sql));
        List<T> entities = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            this.assignValues(preparedStatement, values);
            ResultSet resultSet = preparedStatement.executeQuery();
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
