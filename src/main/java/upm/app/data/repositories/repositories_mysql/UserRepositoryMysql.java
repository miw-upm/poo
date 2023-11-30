package upm.app.data.repositories.repositories_mysql;

import upm.app.data.models.User;
import upm.app.data.repositories.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryMysql extends GenericRepositoryMysql<User> implements UserRepository {

    public UserRepositoryMysql() {
        this.initializeTable();
    }

    private void initializeTable() {
        this.executeUpdate("CREATE TABLE IF NOT EXISTS user(" +
                "id SERIAL PRIMARY KEY," + //id auto increment
                "mobile INT," +
                "name VARCHAR(20)," +
                "address VARCHAR(20))");
    }

    @Override
    public User create(User entity) {
        int id = this.executeInsertGeneratedKey("INSERT INTO user (mobile, name, address) VALUES (?,?,?)",
                entity.getMobile(), entity.getName(), entity.getAddress());
        return this.read(id).orElseThrow();
    }

    @Override
    public Optional<User> read(Integer id) {
        return this.executeQueryConvert("SELECT id, mobile, name, address FROM user WHERE id = ?", id).stream()
                .findFirst();
    }

    @Override
    public User update(User entity) {
        this.executeUpdate("UPDATE user SET mobile = ?, name = ?, address = ? WHERE id = ?",
                entity.getMobile(), entity.getName(), entity.getAddress(), entity.getId());
        return this.read(entity.getId()).orElseThrow();
    }

    @Override
    public void deleteById(Integer id) {
        this.executeUpdate("DELETE FROM user WHERE id = ?", id);
    }

    @Override
    public List<User> findAll() {
        return this.executeQueryConvert("SELECT id, mobile, name, address FROM user");
    }

    @Override
    protected User convertToEntity(ResultSet resultSet) {
        try {
            return new User(resultSet.getInt("id"), resultSet.getInt("mobile"),
                    resultSet.getString("name"), resultSet.getString("address"));
        } catch (SQLException e) {
            throw new RuntimeException("Retriever user error: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> findByMobile(Integer mobile) {
        return this.executeQueryConvert("SELECT id, mobile, name, address FROM user WHERE mobile = ?", mobile).stream()
                .findFirst();
    }
}