package upm.app.data.repositories.repositories_mysql;

import upm.app.data.models.ArticleItem;
import upm.app.data.models.ShoppingCart;
import upm.app.data.repositories.ShoppingCartRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class ShoppingCartRepositoryMysql extends GenericRepositoryMysql<ShoppingCart> implements ShoppingCartRepository {
    private final UserRepositoryMysql userRepositoryMysql;
    private final ArticleRepositoryMysql articleRepositoryMysql;

    public ShoppingCartRepositoryMysql(UserRepositoryMysql userRepositoryMysql, ArticleRepositoryMysql articleRepositoryMysql) {
        this.userRepositoryMysql = userRepositoryMysql;
        this.articleRepositoryMysql = articleRepositoryMysql;
        this.initializeTable();
    }

    private void initializeTable() {
        this.executeUpdate("CREATE TABLE IF NOT EXISTS shoppingCart(" +
                "id SERIAL PRIMARY KEY," + //id auto increment
                "user_id INT NOT NULL," +
                "creationDate TIMESTAMP)");

        this.executeUpdate("CREATE TABLE IF NOT EXISTS article_item(" +
                "id SERIAL PRIMARY KEY," +
                "shoppingCart_id BIGINT UNSIGNED," +
                "article_id BIGINT UNSIGNED," +
                "amount INT," +
                "discount DECIMAL," +
                "FOREIGN KEY (shoppingCart_id) REFERENCES shoppingCart(id)," +
                "FOREIGN KEY (article_id) REFERENCES article(id))");
    }

    @Override
    public ShoppingCart create(ShoppingCart entity) {
        int id = this.executeInsertGeneratedKey(
                "INSERT INTO shoppingCart (user_id, creationDate) VALUES (?, TIMESTAMP ?)", entity.getUser().getId(),
                Timestamp.valueOf(entity.getCreationDate()));
        this.createRelations(id, entity.getArticleItems());
        return this.read(id).orElseThrow();
    }

    private void createRelations(Integer shoppingCartId, List<ArticleItem> articleItems) {
        for (ArticleItem articleItem : articleItems) {
            this.executeUpdate("INSERT INTO article_item (shoppingCart_id, article_id, amount, discount) VALUES (?, ?, ?, ?)",
                    shoppingCartId, articleItem.getArticle().getId(),
                    articleItem.getAmount(), articleItem.getDiscount());
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart entity) {
        this.executeUpdate("UPDATE shoppingCart SET user_id = ?, creationDate = TIMESTAMP ? WHERE id = ?",
                entity.getUser().getId(), Timestamp.valueOf(entity.getCreationDate()), entity.getId());
        this.deleteArticleItemsByShoppingCartId(entity.getId());
        this.createRelations(entity.getId(), entity.getArticleItems());
        return this.read(entity.getId())
                .orElseThrow();
    }

    @Override
    public Optional<ShoppingCart> read(Integer id) {
        Optional<ShoppingCart> shoppingCart = this.executeQueryConvert(
                "SELECT id, user_id, creationDate FROM shoppingCart WHERE id = ?", id).stream()
                .findFirst();
        if (shoppingCart.isPresent()) {
            shoppingCart.get().setArticleItems(this.readArticleItems(shoppingCart.get()));
        }
        return shoppingCart;
    }

    private List<ArticleItem> readArticleItems(ShoppingCart shoppingCart) {
        String sql = "SELECT article_id, amount, discount FROM article_item WHERE shoppingCart_id = ?";
        return this.executeQueryFunctional(sql, resultSet -> {
            try {
                return new ArticleItem(this.articleRepositoryMysql.read(resultSet.getInt("article_id")).orElseThrow(),
                        resultSet.getInt("amount"), resultSet.getBigDecimal("discount"));
            } catch (SQLException e) {
                throw new UnsupportedOperationException("SQL: " + sql + " ===>>> " + e);
            }
        }, shoppingCart.getId());
    }

    @Override
    public void deleteById(Integer id) {
        this.deleteArticleItemsByShoppingCartId(id);
        this.executeUpdate("DELETE FROM shoppingCart WHERE id = ?", id);
    }

    private void deleteArticleItemsByShoppingCartId(Integer shoppingCartId) {
        this.executeUpdate("DELETE FROM article_item WHERE shoppingCart_id = ?", shoppingCartId);
    }

    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> shoppingCarts = this.executeQueryConvert("SELECT id, user_id, creationDate FROM shoppingCart");
        for (ShoppingCart shoppingCart : shoppingCarts) {
            this.readArticleItems(shoppingCart);
        }
        return shoppingCarts;
    }

    @Override
    protected ShoppingCart convertToEntity(ResultSet resultSet) {
        try {
            return new ShoppingCart(
                    resultSet.getInt("id"),
                    this.userRepositoryMysql.read(resultSet.getInt("user_id")).orElseThrow(),
                    resultSet.getTimestamp("creationDate").toLocalDateTime());
        } catch (SQLException e) {
            throw new RuntimeException("convert To ShoppingCart error: " + e.getMessage());
        }
    }
}
