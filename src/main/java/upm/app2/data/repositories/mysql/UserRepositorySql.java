package upm.app2.data.repositories.mysql;

import upm.app2.data.models.User;
import upm.app2.data.repositories.UserRepository;
import upm.app2.data.repositories.exceptions.RepositoryOperationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepositorySql extends GenericRepositorySql<User> implements UserRepository {

    public UserRepositorySql(Connection connection) {
        super(connection);
        this.initializeTable();
    }

    private void initializeTable() {
        final String sql = """
                CREATE TABLE IF NOT EXISTS UserApp (
                  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                  mobile INT NOT NULL UNIQUE,
                  name VARCHAR(50),
                  address VARCHAR(100)
                );
                """;
        this.executeUpdate(sql);
    }

    @Override
    public User create(User entity) {
        final String sql = "INSERT INTO UserApp (mobile, name, address) VALUES (?,?,?)";
        int id = this.executeInsertGeneratedKey(sql, entity.getMobile(), entity.getName(), entity.getAddress());
        return this.read(id).orElseThrow(
                () -> new RepositoryOperationException("Unexpected database error due to entity not found: " + id));
    }

    @Override
    public Optional<User> read(Integer id) {
        final String sql = "SELECT id, mobile, name, address FROM UserApp WHERE id = ?";
        return this.executeQueryConvert(sql, id).stream().findFirst();
    }

    @Override
    public User update(Integer id, User entity) {
        final String sql = "UPDATE UserApp SET mobile = ?, name = ?, address = ? WHERE id = ?";
        this.executeUpdate(sql, entity.getMobile(), entity.getName(), entity.getAddress(), id);
        return this.read(entity.getId()).orElseThrow(
                () -> new RepositoryOperationException("Unexpected database error due to entity not found: " + entity.getId()));
    }

    @Override
    public void deleteById(Integer id) {
        final String sql = "DELETE FROM UserApp WHERE id = ?";
        this.executeUpdate(sql, id);
    }

    @Override
    public List<User> findAll() {
        final String sql = "SELECT id, mobile, name, address FROM UserApp";
        return this.executeQueryConvert(sql);
    }

    @Override
    public Optional<User> findByMobile(Integer mobile) {
        final String sql = "SELECT id, mobile, name, address FROM UserApp WHERE mobile = ?";
        return this.executeQueryConvert(sql, mobile).stream().findFirst();
    }

    @Override
    protected User convertToEntity(ResultSet resultSet) {
        try {
            User userBd = new User(
                    resultSet.getInt("mobile"),
                    resultSet.getString("name"),
                    resultSet.getString("address")
            );
            userBd.setId(resultSet.getInt("id"));
            return userBd;
        } catch (SQLException e) {
            throw new RepositoryOperationException("Retriever user error: " + e.getMessage());
        }
    }
}