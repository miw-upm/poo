package upm.app.data.repositories.repositories_mysql;

import upm.app.data.models.Article;
import upm.app.data.repositories.ArticleRepository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ArticleRepositoryMysql extends GenericRepositoryMysql<Article> implements ArticleRepository {

    public ArticleRepositoryMysql() {
        this.initializeTable();
    }

    public void initializeTable() {
        this.executeUpdate("CREATE TABLE IF NOT EXISTS Article(" +
                "id SERIAL PRIMARY KEY," + //id auto increment
                "barcode VARCHAR(20) UNIQUE NOT NULL," +
                "summary VARCHAR(20)," +
                "price DECIMAL(5,2)," +
                "registrationDate DATE," +
                "provider VARCHAR(20))");
    }

    @Override
    public Article create(Article entity) {
        int id = this.executeInsertGeneratedKey("INSERT INTO Article (barcode, summary, price, registrationDate, provider) " +
                        "VALUES (?, ?, ?, DATE ?, ?)",
                entity.getBarcode(), entity.getSummary(), entity.getPrice(), Date.valueOf(entity.getRegistrationDate()),
                entity.getProvider());
        return this.read(id).orElseThrow(
                () -> new RuntimeException("Unexpected database error due to entity not found: " + id));
    }

    @Override
    public Optional<Article> read(Integer id) {
        return this.executeQueryConvert(
                        "SELECT id, barcode, summary, price, registrationDate, provider FROM Article WHERE id = ?", id).stream()
                .findFirst();
    }

    @Override
    public Article update(Article entity) {
        this.executeUpdate(
                "UPDATE Article SET barcode = ?, summary = ?, price = ?, registrationDate = DATE ?, provider = ? WHERE id = ?",
                entity.getBarcode(), entity.getSummary(), entity.getPrice(), Date.valueOf(entity.getRegistrationDate()),
                entity.getProvider(), entity.getId());
        return this.read(entity.getId())
                .orElseThrow( () -> new RuntimeException("Unexpected database error due to entity not found: " + entity.getId()));
    }

    @Override
    public void deleteById(Integer id) {
        this.executeUpdate("DELETE FROM Article WHERE id = ?", id);
    }

    @Override
    public List<Article> findAll() {
        return this.executeQueryConvert("SELECT id, barcode, summary, price, registrationDate, provider FROM Article");
    }

    @Override
    protected Article convertToEntity(ResultSet resultSet) {
        try {
            return new Article(resultSet.getInt("id"), resultSet.getString("barcode"),
                    resultSet.getString("summary"), resultSet.getBigDecimal("price"),
                    resultSet.getDate("registrationDate").toLocalDate(), resultSet.getString("provider"));
        } catch (SQLException e) {
            throw new RuntimeException("Convert article error:" + e.getMessage());
        }
    }

    @Override
    public Optional<Article> findByBarcode(String barcode) {
        return this.executeQueryConvert(
                        "SELECT id, barcode, summary, price, registrationDate, provider FROM Article WHERE barcode LIKE ?",
                        barcode).stream()
                .findFirst();
    }
}
