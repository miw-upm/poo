package upm.app2.data.repositories.mysql;

import upm.app2.data.models.Article;
import upm.app2.data.repositories.ArticleRepository;
import upm.app2.data.repositories.exceptions.RepositoryOperationException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ArticleRepositorySql extends GenericRepositorySql<Article> implements ArticleRepository {

    public ArticleRepositorySql(Connection connection) {
        super(connection);
        this.initializeTable();
    }

    public void initializeTable() {
        final String sql = """
                CREATE TABLE IF NOT EXISTS Article (
                  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                  barcode VARCHAR(50) NOT NULL UNIQUE,
                  summary VARCHAR(100),
                  price DECIMAL(8,2),
                  registrationDate DATE,
                  provider VARCHAR(100)
                ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
                """;
        this.executeUpdate(sql);
    }

    @Override
    public Article create(Article entity) {
        final String sql = "INSERT INTO Article (barcode, summary, price, registrationDate, provider) VALUES (?, ?, ?, ?, ?)";
        int id = this.executeInsertGeneratedKey(sql, entity.getBarcode(), entity.getSummary(), entity.getPrice(),
                Date.valueOf(entity.getRegistrationDate()), entity.getProvider());
        return this.read(id).orElseThrow(
                () -> new RepositoryOperationException("Unexpected database error due to entity not found: " + id));
    }

    @Override
    public Optional<Article> read(Integer id) {
        final String sql = "SELECT id, barcode, summary, price, registrationDate, provider FROM Article WHERE id = ?";
        return this.executeQueryConvert(sql, id).stream().findFirst();
    }

    @Override
    public Article update(Integer id, Article entity) {
        final String sql = "UPDATE Article SET barcode = ?, summary = ?, price = ?, registrationDate =  ?, provider = ? WHERE id = ?";
        this.executeUpdate(sql, entity.getBarcode(), entity.getSummary(), entity.getPrice(),
                Date.valueOf(entity.getRegistrationDate()), entity.getProvider(), id);
        return this.read(entity.getId())
                .orElseThrow(() -> new RepositoryOperationException("Unexpected database error due to entity not found: " + entity.getId()));
    }

    @Override
    public void deleteById(Integer id) {
        final String sql = "DELETE FROM Article WHERE id = ?";
        this.executeUpdate(sql, id);
    }

    @Override
    public List<Article> findAll() {
        final String sql = "SELECT id, barcode, summary, price, registrationDate, provider FROM Article";
        return this.executeQueryConvert(sql);
    }

    @Override
    public Optional<Article> findByBarcode(String barcode) {
        final String sql = "SELECT id, barcode, summary, price, registrationDate, provider FROM Article WHERE barcode LIKE ?";
        return this.executeQueryConvert(sql, barcode).stream().findFirst();
    }

    @Override
    protected Article convertToEntity(ResultSet resultSet) {
        try {
            Article articleBd = new Article(resultSet.getString("barcode"),
                    resultSet.getString("summary"), resultSet.getBigDecimal("price"),
                    resultSet.getString("provider"));
            articleBd.setId(resultSet.getInt("id"));
            articleBd.setRegistrationDate(resultSet.getDate("registrationDate").toLocalDate());
            return articleBd;
        } catch (SQLException e) {
            throw new RepositoryOperationException("Convert article error:" + e.getMessage());
        }
    }
}
