package upm.app.data.repositories.repositories_mysql;

import upm.app.data.models.Article;
import upm.app.data.repositories.ArticleRepository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ArticleRepositoryMysql extends GenericRepositoryMysql<Article> implements ArticleRepository {
    private static final String FIELDS = "barcode,summary,price,registrationDate,provider";
    private static final String KEY_FIELDS = "id," + FIELDS;

    public ArticleRepositoryMysql() {
        this.initializeTable();
    }

    private void initializeTable() {
        this.executeUpdate("DROP TABLE IF EXISTS article"); // Only in develop
        this.executeUpdate("CREATE TABLE IF NOT EXISTS article(" +
                KEY_FIELDS.split(",")[0] + " SERIAL PRIMARY KEY," + //id auto increment
                KEY_FIELDS.split(",")[1] + " VARCHAR(20) NOT NULL," +
                KEY_FIELDS.split(",")[2] + " VARCHAR(20)," +
                KEY_FIELDS.split(",")[3] + " DECIMAL," +
                KEY_FIELDS.split(",")[4] + " DATE," +
                KEY_FIELDS.split(",")[5] + " VARCHAR(20))");
    }

    @Override
    public Article create(Article article) {
        String sql = String.format("INSERT INTO article (%s) VALUES ('%s','%s',%s,DATE '%s','%s')", FIELDS,
                article.getBarcode(), article.getSummary(), article.getPrice(),
                Date.valueOf(article.getRegistrationDate()), article.getProvider());
        this.executeUpdate(sql);
        return this.findByBarcode(article.getBarcode()).orElseThrow();
    }

    @Override
    public Optional<Article> read(Integer id) {
        return this.executeQuery(String.format("SELECT %s FROM article WHERE id = %d", KEY_FIELDS, id)).stream()
                .findFirst();
    }

    @Override
    public Article update(Article article) {
        String sql = String.format("UPDATE article SET %s = '%s',%s = '%s',%s = %s,%s = DATE '%s',%s = '%s' " +
                        "WHERE %s = %d",
                KEY_FIELDS.split(",")[1], article.getBarcode(),
                KEY_FIELDS.split(",")[2], article.getSummary(),
                KEY_FIELDS.split(",")[3], article.getPrice(),
                KEY_FIELDS.split(",")[4], Date.valueOf(article.getRegistrationDate()),
                KEY_FIELDS.split(",")[1], article.getProvider(),
                KEY_FIELDS.split(",")[0], article.getId());
        this.executeUpdate(sql);
        return this.read(article.getId())
                .orElseThrow();
    }

    @Override
    public void deleteById(Integer id) {
        this.executeUpdate(String.format("DELETE FROM article WHERE id = %d", id));
    }

    @Override
    public List<Article> findAll() {
        return this.executeQuery(String.format("SELECT %s FROM article", KEY_FIELDS));
    }

    @Override
    public Optional<Article> findByBarcode(String barcode) {
        return this.executeQuery(String.format("SELECT %s FROM article WHERE barcode like %s", KEY_FIELDS, barcode)).stream()
                .findFirst();
    }

    @Override
    protected Article retriever(ResultSet resultSet) {
        try {
            return new Article(
                    resultSet.getInt(KEY_FIELDS.split(",")[0]),
                    resultSet.getString(KEY_FIELDS.split(",")[1]),
                    resultSet.getString(KEY_FIELDS.split(",")[2]),
                    resultSet.getBigDecimal(KEY_FIELDS.split(",")[3]),
                    resultSet.getDate(KEY_FIELDS.split(",")[4]).toLocalDate(),
                    resultSet.getString(KEY_FIELDS.split(",")[5]));
        } catch (SQLException e) {
            throw new RuntimeException("Retriever article error:" + e.getMessage());
        }
    }

}
