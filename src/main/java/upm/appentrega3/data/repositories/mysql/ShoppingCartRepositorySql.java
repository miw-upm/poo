package upm.appentrega3.data.repositories.mysql;

import upm.appentrega3.data.models.ArticleItem;
import upm.appentrega3.data.models.ShoppingCart;
import upm.appentrega3.data.repositories.ShoppingCartRepository;

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
        this.executeUpdate("CREATE TABLE IF NOT EXISTS ShoppingCart (" +
                "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," + //id auto increment
                "user_id INT NOT NULL," +
                "creationDate TIMESTAMP)");

        this.executeUpdate("CREATE TABLE IF NOT EXISTS ArticleItem (" +
                "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
                "shoppingCart_id INT," +
                "article_id INT," +
                "amount INT," +
                "discount DECIMAL(4,2)," +
                "FOREIGN KEY (shoppingCart_id) REFERENCES shoppingCart(id)," +
                "FOREIGN KEY (article_id) REFERENCES article(id))");
    }

    @Override
    public ShoppingCart create(ShoppingCart entity) {
        int id = this.executeInsertGeneratedKey(
                "INSERT INTO ShoppingCart (user_id, creationDate) VALUES (?, ?)", entity.getUser().getId(),
                Timestamp.valueOf(entity.getCreationDate()));
        this.createRelations(id, entity.getArticleItems());
        return this.read(id).orElseThrow(
                () -> new RuntimeException("Unexpected database error due to entity not found: " + id));
    }

    private void createRelations(Integer shoppingCartId, List<ArticleItem> articleItems) {
        for (ArticleItem articleItem : articleItems) {
            this.executeUpdate("INSERT INTO ArticleItem (shoppingCart_id, article_id, amount, discount) VALUES (?, ?, ?, ?)",
                    shoppingCartId, articleItem.getArticle().getId(),
                    articleItem.getAmount(), articleItem.getDiscount());
        }
    }

    @Override
    public Optional<ShoppingCart> read(Integer id) {
        Optional<ShoppingCart> shoppingCart = this.executeQueryConvert(
                        "SELECT id, user_id, creationDate FROM ShoppingCart WHERE id = ?", id).stream()
                .findFirst();
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
                throw new UnsupportedOperationException("SQL: " + sql + " ===>>> " + e);
            }
        }, shoppingCart.getId());
    }

    @Override
    public ShoppingCart update(ShoppingCart entity) {
        this.executeUpdate("UPDATE ShoppingCart SET user_id = ?, creationDate = ? WHERE id = ?",
                entity.getUser().getId(), Timestamp.valueOf(entity.getCreationDate()), entity.getId());
        this.deleteArticleItemsByShoppingCartId(entity.getId());
        this.createRelations(entity.getId(), entity.getArticleItems());
        return this.read(entity.getId())
                .orElseThrow(() -> new RuntimeException("Unexpected database error due to entity not found: " + entity.getId()));
    }

    @Override
    public void deleteById(Integer id) {
        this.deleteArticleItemsByShoppingCartId(id);
        this.executeUpdate("DELETE FROM ShoppingCart WHERE id = ?", id);
    }

    private void deleteArticleItemsByShoppingCartId(Integer shoppingCartId) {
        this.executeUpdate("DELETE FROM ArticleItem WHERE shoppingCart_id = ?", shoppingCartId);
    }

    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> shoppingCarts = this.executeQueryConvert("SELECT id, user_id, creationDate FROM ShoppingCart");
        for (ShoppingCart shoppingCart : shoppingCarts) {
            this.readArticleItems(shoppingCart);
        }
        return shoppingCarts;
    }

    @Override
    protected ShoppingCart convertToEntity(ResultSet resultSet) {
        try {
            ShoppingCart shoppingCartBd =  new ShoppingCart(
                    this.userRepositorySql.read(resultSet.getInt("user_id")).orElseThrow(),
                    resultSet.getTimestamp("creationDate").toLocalDateTime());
            shoppingCartBd.setId( resultSet.getInt("id"));
            return shoppingCartBd;
        } catch (SQLException e) {
            throw new UnsupportedOperationException("convert To ShoppingCart error: " + e.getMessage());
        }
    }
}
