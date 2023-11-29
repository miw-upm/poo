package upm.app.data.repositories.repositories_mysql;

import upm.app.data.models.Article;
import upm.app.data.repositories.ArticleRepository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ArticleRepositoryMysql extends GenericRepositoryMysql<Article> implements ArticleRepository {
    private static final String TABLE = "article";
    private static final String FIELDS = "barcode,summary,price,registrationDate,provider";
    private static final String KEY_FIELDS = "id," + FIELDS;

    public ArticleRepositoryMysql() {
        this.initializeTable();
    }

    public void initializeTable() {
        this.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                KEY_FIELDS.split(",")[0] + " SERIAL PRIMARY KEY," + //id auto increment
                KEY_FIELDS.split(",")[1] + " VARCHAR(20) UNIQUE NOT NULL," +
                KEY_FIELDS.split(",")[2] + " VARCHAR(20)," +
                KEY_FIELDS.split(",")[3] + " DECIMAL," +
                KEY_FIELDS.split(",")[4] + " DATE," +
                KEY_FIELDS.split(",")[5] + " VARCHAR(20))");
    }

    @Override
    public Article create(Article entity) {
        String sql = String.format("INSERT INTO %s (%s) VALUES ('%s', '%s', %s, DATE '%s', '%s')", TABLE, FIELDS,
                entity.getBarcode(), entity.getSummary(), entity.getPrice(), Date.valueOf(entity.getRegistrationDate()),
                entity.getProvider());
        return this.read(this.executeInsertGeneratedKey(sql)).orElseThrow();
    }

    @Override
    public Optional<Article> read(Integer id) {
        return this.executeQueryConvert(String.format("SELECT %s FROM %s WHERE id = %d", KEY_FIELDS, TABLE, id)).stream()
                .findFirst();
    }

    @Override
    public Article update(Article entity) {
        String sql = String.format("UPDATE %s SET " +
                        "%s = '%s', %s = '%s', %s = %s, %s = DATE '%s', %s = '%s' " +
                        "WHERE %s = %d",
                TABLE,
                KEY_FIELDS.split(",")[1], entity.getBarcode(),
                KEY_FIELDS.split(",")[2], entity.getSummary(),
                KEY_FIELDS.split(",")[3], entity.getPrice(),
                KEY_FIELDS.split(",")[4], Date.valueOf(entity.getRegistrationDate()),
                KEY_FIELDS.split(",")[5], entity.getProvider(),
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
    public List<Article> findAll() {
        return this.executeQueryConvert(String.format("SELECT %s FROM %s", KEY_FIELDS, TABLE));
    }

    @Override
    public Optional<Article> findByBarcode(String barcode) {
        return this.executeQueryConvert(String.format("SELECT %s FROM %s WHERE %s like %s", KEY_FIELDS, TABLE, KEY_FIELDS.split(",")[1], barcode)).stream()
                .findFirst();
    }

    @Override
    protected Article convertToEntity(ResultSet resultSet) {
        try {
            return new Article(
                    resultSet.getInt(KEY_FIELDS.split(",")[0]),
                    resultSet.getString(KEY_FIELDS.split(",")[1]),
                    resultSet.getString(KEY_FIELDS.split(",")[2]),
                    resultSet.getBigDecimal(KEY_FIELDS.split(",")[3]),
                    resultSet.getDate(KEY_FIELDS.split(",")[4]).toLocalDate(),
                    resultSet.getString(KEY_FIELDS.split(",")[5]));
        } catch (SQLException e) {
            throw new RuntimeException("Convert article error:" + e.getMessage());
        }
    }

}
