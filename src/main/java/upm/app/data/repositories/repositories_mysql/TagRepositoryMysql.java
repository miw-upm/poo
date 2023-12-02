package upm.app.data.repositories.repositories_mysql;

import upm.app.data.models.Article;
import upm.app.data.models.Tag;
import upm.app.data.repositories.TagRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TagRepositoryMysql extends GenericRepositoryMysql<Tag> implements TagRepository {
    private final ArticleRepositoryMysql articleRepositoryMysql;

    public TagRepositoryMysql(ArticleRepositoryMysql articleRepositoryMysql) {
        this.articleRepositoryMysql = articleRepositoryMysql;
        this.initializeTable();
    }

    private void initializeTable() {
        this.executeUpdate("CREATE TABLE IF NOT EXISTS Tag(" +
                "id SERIAL PRIMARY KEY," + //id auto increment
                "name VARCHAR(20) UNIQUE NOT NULL," +
                "description VARCHAR(20))");

        this.executeUpdate("CREATE TABLE IF NOT EXISTS tag_article(" +
                "tag_id BIGINT UNSIGNED," +
                "article_id BIGINT UNSIGNED," +
                "PRIMARY KEY (tag_id, article_id)," +
                "FOREIGN KEY (tag_id) REFERENCES Tag(id)," +
                "FOREIGN KEY (article_id) REFERENCES Article(id))");
    }

    @Override
    public Tag create(Tag tag) {
        int id = this.executeInsertGeneratedKey("INSERT INTO Tag (name, description) VALUES (?,?)", tag.getName(), tag.getDescription());
        createRelations(id, tag.getArticles());
        return this.findByName(tag.getName()).orElseThrow(() -> new RuntimeException("Unexpected database error due to entity not found: " + id));
    }

    private void createRelations(int tagId, List<Article> articles) {
        for (Article article : articles) {
            Article retrieverArticle = this.articleRepositoryMysql.findByBarcode(article.getBarcode()).orElseThrow(
                    () -> new RuntimeException("Article not found: " + article.getBarcode())
            );
            this.executeUpdate("INSERT INTO tag_article (tag_id, article_id) VALUES (?,?)", tagId, retrieverArticle.getId());
        }
    }

    @Override
    public Optional<Tag> read(Integer id) {
        Optional<Tag> tag = this.executeQueryConvert("SELECT id, name, description FROM Tag WHERE id = ?", id).stream()
                .findFirst();
        if (tag.isPresent()) {
            tag.get().setArticles(this.readTagArticles(tag.get()));
        }
        return tag;
    }

    private List<Article> readTagArticles(Tag tag) {
        String sql = "SELECT article_id FROM tag_article WHERE tag_id = ?";
        return this.executeQueryFunctional(sql, resultSet -> {
            try {
                return this.articleRepositoryMysql.read(resultSet.getInt("article_id")).orElseThrow();
            } catch (SQLException e) {
                throw new UnsupportedOperationException("SQL: " + sql + " ===>>> " + e);
            }
        }, tag.getId());
    }

    @Override
    public Tag update(Tag entity) {
        this.executeUpdate("UPDATE Tag SET name = ?, description = ? WHERE id = ?",
                entity.getName(), entity.getDescription(), entity.getId());
        this.deleteArticlesByTagId(entity.getId());
        this.createRelations(entity.getId(), entity.getArticles());
        return this.read(entity.getId())
                .orElseThrow(() -> new RuntimeException("Unexpected database error due to entity not found: " + entity.getId()));
    }

    private void deleteArticlesByTagId(Integer tagId) {
        this.executeUpdate("DELETE FROM tag_article WHERE tag_id = ?", tagId);
    }

    @Override
    public void deleteById(Integer id) {
        this.deleteArticlesByTagId(id);
        this.executeUpdate("DELETE FROM Tag WHERE id = ?", id);
    }

    @Override
    public List<Tag> findAll() {
        List<Tag> tags = this.executeQueryConvert("SELECT id, name, description FROM Tag");
        for (Tag tag : tags) {
            this.readTagArticles(tag);
        }
        return tags;
    }

    @Override
    protected Tag convertToEntity(ResultSet resultSet) {
        try {
            return new Tag(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"));
        } catch (SQLException e) {
            throw new RuntimeException("convertToTag error: " + e.getMessage());
        }
    }

    @Override
    public Optional<Tag> findByName(String name) {
        Optional<Tag> retrieverTag = this.executeQueryConvert(
                        "SELECT id, name, description FROM Tag WHERE name = ?", name).stream()
                .findFirst();
        if (retrieverTag.isPresent()) {
           retrieverTag.get().setArticles(this.readTagArticles(retrieverTag.get()));
        }
        return retrieverTag;
    }
}
