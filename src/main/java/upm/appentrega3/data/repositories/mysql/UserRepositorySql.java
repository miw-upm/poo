package upm.appentrega3.data.repositories.mysql;

import upm.appentrega3.data.models.Rol;
import upm.appentrega3.data.models.User;
import upm.appentrega3.data.repositories.UserRepository;

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
        this.executeUpdate("CREATE TABLE IF NOT EXISTS UserApp (" +
                "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT ," + //id auto increment
                "mobile INT UNIQUE NOT NULL," +
                "password VARCHAR(20)," +
                "name VARCHAR(20)," +
                "address VARCHAR(20)," +
                "rol VARCHAR(20))");
    }

    @Override
    public User create(User entity) {
        int id = this.executeInsertGeneratedKey("INSERT INTO UserApp (mobile, password, name, address, rol) VALUES (?,?,?,?,?)",
                entity.getMobile(), entity.getPassword(), entity.getName(), entity.getAddress(), entity.getRol().name());
        return this.read(id).orElseThrow(
                () -> new RuntimeException("Unexpected database error due to entity not found: " + id));
    }

    @Override
    public Optional<User> read(Integer id) {
        return this.executeQueryConvert("SELECT id, mobile, password, name, address, rol FROM UserApp WHERE id = ?", id).stream()
                .findFirst();
    }

    @Override
    protected User convertToEntity(ResultSet resultSet) {
        try {
            User userBd = new User(resultSet.getInt("mobile"), resultSet.getString("password"),
                    resultSet.getString("name"), resultSet.getString("address"));
            userBd.setId(resultSet.getInt("id"));
            userBd.setRol(Rol.valueOf(resultSet.getString("rol")));
            return userBd;
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Retriever user error: " + e.getMessage());
        }
    }

    @Override
    public User update(User entity) {
        this.executeUpdate("UPDATE UserApp SET mobile = ?, password = ?, name = ?, address = ?, rol = ? WHERE id = ?",
                entity.getMobile(), entity.getPassword(), entity.getName(), entity.getAddress(), entity.getRol().name(), entity.getId());
        return this.read(entity.getId()).orElseThrow(
                () -> new RuntimeException("Unexpected database error due to entity not found: " + entity.getId()));
    }

    @Override
    public void deleteById(Integer id) {
        this.executeUpdate("DELETE FROM UserApp WHERE id = ?", id);
    }

    @Override
    public List<User> findAll() {
        return this.executeQueryConvert("SELECT id, mobile, password, name, address, rol FROM UserApp");
    }

    @Override
    public Optional<User> findByMobile(Integer mobile) {
        return this.executeQueryConvert("SELECT id, mobile, password, name, address, rol FROM UserApp WHERE mobile = ?", mobile).stream()
                .findFirst();
    }
}