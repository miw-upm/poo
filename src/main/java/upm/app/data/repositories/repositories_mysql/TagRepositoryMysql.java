package upm.app.data.repositories.repositories_mysql;

import upm.app.data.models.Article;
import upm.app.data.models.Tag;
import upm.app.data.repositories.TagRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TagRepositoryMysql extends GenericRepositoryMysql<Tag> implements TagRepository {
    private static final String TABLE = "tag";
    private static final String RELATION = "tag_article";
    private static final String FIELDS = "name,description";
    private static final String KEY_FIELDS = "id," + FIELDS;

    private final ArticleRepositoryMysql articleRepositoryMysql;

    public TagRepositoryMysql(ArticleRepositoryMysql articleRepositoryMysql) {
        this.articleRepositoryMysql = articleRepositoryMysql;
        this.initializeTable();
    }

    private void initializeTable() {
        this.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                KEY_FIELDS.split(",")[0] + " SERIAL PRIMARY KEY," + //id auto increment
                KEY_FIELDS.split(",")[1] + " VARCHAR(20) UNIQUE NOT NULL," +
                KEY_FIELDS.split(",")[2] + " VARCHAR(20))");

        this.executeUpdate("CREATE TABLE IF NOT EXISTS " + RELATION + "(" +
                "tag_id BIGINT UNSIGNED," +
                "article_id BIGINT UNSIGNED," +
                "PRIMARY KEY (tag_id, article_id)," +
                "FOREIGN KEY (tag_id) REFERENCES tag(id)," +
                "FOREIGN KEY (article_id) REFERENCES article(id) )");
    }

    @Override
    public Tag create(Tag tag) {
        int id = this.executeInsertGeneratedKey(String.format(
                "INSERT INTO %s (%s) VALUES ('%s','%s')", TABLE, FIELDS, tag.getName(), tag.getDescription()));
        createRelations(id, tag.getArticles());
        return this.findByName(tag.getName()).orElseThrow();
    }

    private void createRelations(Integer tagId, List<Article> articles) {
        for (Article article : articles) {
            Article retrieverArticle = this.articleRepositoryMysql.findByBarcode(article.getBarcode()).orElseThrow();
            this.executeUpdate(String.format(
                    "INSERT INTO %s (tag_id, article_id) VALUES (%d,%d)", RELATION, tagId, retrieverArticle.getId()));
        }
    }

    @Override
    public Optional<Tag> read(Integer id) {
        Optional<Tag> tag = this.executeQueryConvert(String.format("SELECT %s FROM %s WHERE id = %d", KEY_FIELDS, TABLE, id)).stream()
                .findFirst();
        if (tag.isPresent()) {
            this.readTagArticles(tag.get());
        }
        return tag;
    }

    private void readTagArticles(Tag tag) {
        ResultSet resultSet = this.executeQuery(String.format(
                "SELECT article_id FROM %s WHERE tag_id = %d", RELATION, tag.getId()));
        try {
            while (resultSet.next()) {
                tag.addArticle(this.articleRepositoryMysql.read(resultSet.getInt("article_id")).orElseThrow());
            }
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Read article " + TABLE + " error: " + e.getMessage());
        }
    }

    @Override
    public Tag update(Tag entity) {
        this.executeUpdate(String.format("UPDATE %s SET %s = '%s', %s = '%s' WHERE %s = %d",
                TABLE,
                KEY_FIELDS.split(",")[1], entity.getName(),
                KEY_FIELDS.split(",")[2], entity.getDescription(),
                KEY_FIELDS.split(",")[0], entity.getId()));
        this.deleteArticlesByTagId(entity.getId());
        this.createRelations(entity.getId(), entity.getArticles());
        return this.read(entity.getId())
                .orElseThrow();
    }

    private void deleteArticlesByTagId(Integer tagId) {
        this.executeUpdate(String.format("DELETE FROM %s WHERE tag_id = %d", RELATION, tagId));
    }

    @Override
    public void deleteById(Integer id) {
        this.deleteArticlesByTagId(id);
        this.executeUpdate(String.format("DELETE FROM %s WHERE id = %d", TABLE, id));
    }

    @Override
    public List<Tag> findAll() {
        List<Tag> tags = this.executeQueryConvert(String.format("SELECT %s FROM %s", KEY_FIELDS, TABLE));
        for (Tag tag : tags) {
            this.readTagArticles(tag);
        }
        return tags;
    }

    @Override
    protected Tag convertToEntity(ResultSet resultSet) {
        try {
            return new Tag(
                    resultSet.getInt(KEY_FIELDS.split(",")[0]),
                    resultSet.getString(KEY_FIELDS.split(",")[1]),
                    resultSet.getString(KEY_FIELDS.split(",")[2]));
        } catch (SQLException e) {
            throw new RuntimeException("convertToTag " + TABLE + " error: " + e.getMessage());
        }
    }

    @Override
    public Optional<Tag> findByName(String name) {
        Optional<Tag> retrieverTag = this.executeQueryConvert(String.format(
                "SELECT %s FROM %s WHERE %s = '%s'", KEY_FIELDS, TABLE, KEY_FIELDS.split(",")[1], name)).stream()
                .findFirst();
        if (retrieverTag.isPresent()) {
            this.readTagArticles(retrieverTag.get());
        }
        return retrieverTag;
    }

}
