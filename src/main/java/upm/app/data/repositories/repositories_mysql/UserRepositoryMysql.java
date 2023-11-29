package upm.app.data.repositories.repositories_mysql;

import upm.app.data.models.User;
import upm.app.data.repositories.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryMysql extends GenericRepositoryMysql<User> implements UserRepository {
    private static final String TABLE = "user";
    private static final String FIELDS = "mobile,name,address";
    private static final String KEY_FIELDS = "id," + FIELDS;

    public UserRepositoryMysql() {
        this.initializeTable();
    }

    private void initializeTable() {
        this.executeUpdate("DROP TABLE IF EXISTS " + TABLE); // Only in develop
        this.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                KEY_FIELDS.split(",")[0] + " SERIAL PRIMARY KEY," + //id auto increment
                KEY_FIELDS.split(",")[1] + " INT NOT NULL," +
                KEY_FIELDS.split(",")[2] + " VARCHAR(20)," +
                KEY_FIELDS.split(",")[3] + " VARCHAR(20))");
    }

    @Override
    public User create(User user) {
        String sql = String.format("INSERT INTO %s (%s) VALUES (%d,'%s','%s')", TABLE, FIELDS,
                user.getMobile(), user.getName(), user.getAddress());
        this.executeUpdate(sql);
        return this.findByMobile(user.getMobile()).orElseThrow();
    }

    @Override
    public Optional<User> read(Integer id) {
        return this.executeQuery(String.format("SELECT %s FROM %s WHERE id = %d", KEY_FIELDS, TABLE, id)).stream()
                .findFirst();
    }

    @Override
    public User update(User entity) {
        String sql = String.format("UPDATE %s SET " +
                        "%s = %d, %s = '%s', %s = '%s' " +
                        "WHERE %s = %d",
                TABLE,
                KEY_FIELDS.split(",")[1], entity.getMobile(),
                KEY_FIELDS.split(",")[2], entity.getName(),
                KEY_FIELDS.split(",")[3], entity.getAddress(),
                KEY_FIELDS.split(",")[0], entity.getId());
        this.executeUpdate(sql);
        return this.read(entity.getId())
                .orElseThrow();
    }

    @Override
    public void deleteById(Integer id) {
        this.executeUpdate(String.format("DELETE FROM %s WHERE id = %d", TABLE, id));
    }

    @Override
    public List<User> findAll() {
        return this.executeQuery(String.format("SELECT %s FROM %s", KEY_FIELDS, TABLE));
    }

    @Override
    protected User retriever(ResultSet resultSet) {
        try {
            return new User(
                    resultSet.getInt(KEY_FIELDS.split(",")[0]),
                    resultSet.getInt(KEY_FIELDS.split(",")[1]),
                    resultSet.getString(KEY_FIELDS.split(",")[2]),
                    resultSet.getString(KEY_FIELDS.split(",")[3]));
        } catch (SQLException e) {
            throw new RuntimeException("Retriever " + TABLE + " error: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> findByMobile(Integer mobile) {
        return this.executeQuery(String.format("SELECT %s FROM %s WHERE %s = %d", KEY_FIELDS, TABLE, KEY_FIELDS.split(",")[1], mobile)).stream()
                .findFirst();
    }

}
