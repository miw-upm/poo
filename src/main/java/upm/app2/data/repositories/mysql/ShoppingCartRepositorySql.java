package upm.app2.data.repositories.mysql;

import upm.app2.data.models.ArticleItem;
import upm.app2.data.models.ShoppingCart;
import upm.app2.data.repositories.ShoppingCartRepository;
import upm.app2.data.repositories.exceptions.RepositoryOperationException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class ShoppingCartRepositorySql extends GenericRepositorySql<ShoppingCart> implements ShoppingCartRepository {
    private final UserRepositorySql userRepositorySql;
    private final ArticleRepositorySql articleRepositoryMysql;

    public ShoppingCartRepositorySql(Connection connection, UserRepositorySql userRepositorySql, ArticleRepositorySql articleRepositorySql) {
        super(connection);
        this.userRepositorySql = userRepositorySql;
        this.articleRepositoryMysql = articleRepositorySql;
        this.initializeTable();
    }

    private void initializeTable() {
        final String sqlShoppingCart = """
                CREATE TABLE IF NOT EXISTS ShoppingCart (
                  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                  user_id INT NOT NULL,
                  creationDate TIMESTAMP
                );
                """;
        final String sqlArticleItem = """
                CREATE TABLE IF NOT EXISTS ArticleItem (
                  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                  shoppingCart_id INT NOT NULL,
                  article_id INT NOT NULL,
                  amount INT NOT NULL,
                  discount DECIMAL(5,2) NOT NULL DEFAULT 0,
                  FOREIGN KEY (shoppingCart_id) REFERENCES ShoppingCart(id) ON DELETE CASCADE,
                  FOREIGN KEY (article_id) REFERENCES Article(id)
                );
                """;
        this.executeUpdate(sqlShoppingCart);
        this.executeUpdate(sqlArticleItem);
    }

    @Override
    public ShoppingCart create(ShoppingCart entity) {
        final String sql = "INSERT INTO ShoppingCart (user_id, creationDate) VALUES (?, ?)";
        int id = this.executeInsertGeneratedKey(sql, entity.getUser().getId(), Timestamp.valueOf(entity.getCreationDate()));
        this.createRelations(id, entity.getArticleItems());
        return this.read(id).orElseThrow(
                () -> new RepositoryOperationException("Unexpected database error due to entity not found: " + id));
    }

    private void createRelations(Integer shoppingCartId, List<ArticleItem> articleItems) {
        final String sql = "INSERT INTO ArticleItem (shoppingCart_id, article_id, amount, discount) VALUES (?, ?, ?, ?)";
        for (ArticleItem articleItem : articleItems) {
            this.executeUpdate(sql, shoppingCartId, articleItem.getArticle().getId(), articleItem.getAmount(), articleItem.getDiscount());
        }
    }

    @Override
    public Optional<ShoppingCart> read(Integer id) {
        final String sql = "SELECT id, user_id, creationDate FROM ShoppingCart WHERE id = ?";
        Optional<ShoppingCart> shoppingCart = this.executeQueryConvert(sql, id).stream().findFirst();
        shoppingCart.ifPresent(cart -> cart.setArticleItems(this.readArticleItems(cart)));
        return shoppingCart;
    }

    private List<ArticleItem> readArticleItems(ShoppingCart shoppingCart) {
        String sql = "SELECT article_id, amount, discount FROM ArticleItem WHERE shoppingCart_id = ?";
        return this.executeQueryFunctional(sql, resultSet -> {
            try {
                return new ArticleItem(this.articleRepositoryMysql.read(resultSet.getInt("article_id")).orElseThrow(),
                        resultSet.getInt("amount"), resultSet.getBigDecimal("discount"));
            } catch (SQLException e) {
                throw new RepositoryOperationException("SQL: " + sql + " ===>>> " + e);
            }
        }, shoppingCart.getId());
    }

    @Override
    public ShoppingCart update(Integer id, ShoppingCart entity) {
        String sql = "UPDATE ShoppingCart SET user_id = ?, creationDate = ? WHERE id = ?";
        this.executeUpdate(sql, entity.getUser().getId(), Timestamp.valueOf(entity.getCreationDate()), id);
        this.deleteArticleItemsByShoppingCartId(id);
        this.createRelations(entity.getId(), entity.getArticleItems());
        return this.read(id)
                .orElseThrow(() -> new RepositoryOperationException("Unexpected database error due to entity not found: " + entity.getId()));
    }

    private void deleteArticleItemsByShoppingCartId(Integer shoppingCartId) {
        String sql = "DELETE FROM ArticleItem WHERE shoppingCart_id = ?";
        this.executeUpdate(sql, shoppingCartId);
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM ShoppingCart WHERE id = ?";
        this.executeUpdate(sql, id);
    }

    @Override
    public List<ShoppingCart> findAll() {
        String sql = "SELECT id, user_id, creationDate FROM ShoppingCart";
        List<ShoppingCart> carts = this.executeQueryConvert(sql);
        for (ShoppingCart cart : carts) {
            cart.setArticleItems(this.readArticleItems(cart));
        }
        return carts;
    }

    @Override
    protected ShoppingCart convertToEntity(ResultSet resultSet) {
        try {
            ShoppingCart shoppingCartBd = new ShoppingCart(
                    this.userRepositorySql.read(resultSet.getInt("user_id")).orElseThrow(),
                    resultSet.getTimestamp("creationDate").toLocalDateTime());
            shoppingCartBd.setId(resultSet.getInt("id"));
            return shoppingCartBd;
        } catch (SQLException e) {
            throw new RepositoryOperationException("convert To ShoppingCart error: " + e.getMessage());
        }
    }
}
