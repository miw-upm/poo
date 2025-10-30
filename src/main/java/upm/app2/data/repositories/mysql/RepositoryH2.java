package upm.app2.data.repositories.mysql;

import upm.app2.data.repositories.exceptions.RepositoryOperationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryH2 {
    private static final String URL = "jdbc:h2:mem:test";

    public Connection createConnection() {
        try {
            return DriverManager.getConnection(RepositoryH2.URL);
        } catch (SQLException e) {
            throw new RepositoryOperationException("H2 Connection error with '" + RepositoryH2.URL + "'. " + e.getMessage());
        }
    }

}
