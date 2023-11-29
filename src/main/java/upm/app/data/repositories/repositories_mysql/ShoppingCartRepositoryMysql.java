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
    private static final String TABLE = "shoppingCart";
    private static final String RELATION = "article_item";
    private static final String FIELDS = "user_id,creationDate";
    private static final String KEY_FIELDS = "id," + FIELDS;
    private final UserRepositoryMysql userRepositoryMysql;
    private final ArticleRepositoryMysql articleRepositoryMysql;

    public ShoppingCartRepositoryMysql(UserRepositoryMysql userRepositoryMysql, ArticleRepositoryMysql articleRepositoryMysql) {
        this.userRepositoryMysql = userRepositoryMysql;
        this.articleRepositoryMysql = articleRepositoryMysql;
        this.initializeTable();
    }

    private void initializeTable() {
        this.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                KEY_FIELDS.split(",")[0] + " SERIAL PRIMARY KEY," + //id auto increment
                KEY_FIELDS.split(",")[1] + " INT NOT NULL," +
                KEY_FIELDS.split(",")[2] + " TIMESTAMP)");

        this.executeUpdate("CREATE TABLE IF NOT EXISTS " + RELATION + "(" +
                "id SERIAL PRIMARY KEY," +
                "shoppingCart_id BIGINT UNSIGNED," +
                "article_id BIGINT UNSIGNED," +
                "amount INT," +
                "discount DECIMAL," +
                "FOREIGN KEY (shoppingCart_id) REFERENCES shoppingCart(id)," +
                "FOREIGN KEY (article_id) REFERENCES article(id) )");
    }


    @Override
    public ShoppingCart create(ShoppingCart entity) {
        int id = this.executeInsertGeneratedKey(String.format(
                "INSERT INTO %s (%s) VALUES (%d, TIMESTAMP '%s')", TABLE, FIELDS, entity.getUser().getId(),
                Timestamp.valueOf(entity.getCreationDate())));
        this.createRelations(id, entity.getArticleItems());
        return this.read(id).orElseThrow();
    }

    private void createRelations(Integer shoppingCartId, List<ArticleItem> articleItems) {
        for (ArticleItem articleItem : articleItems) {
            this.executeUpdate(String.format(
                    "INSERT INTO %s (shoppingCart_id, article_id, amount, discount) VALUES (%d, %d, %d, %s)", RELATION, shoppingCartId, articleItem.getArticle().getId(),
                    articleItem.getAmount(), articleItem.getDiscount()));
        }
    }

    @Override
    public ShoppingCart update(ShoppingCart entity) {
        this.executeUpdate(String.format("UPDATE %s SET %s = %d, %s = TIMESTAMP '%s' WHERE %s = %d",
                TABLE,
                KEY_FIELDS.split(",")[1], entity.getUser().getId(),
                KEY_FIELDS.split(",")[2],  Timestamp.valueOf(entity.getCreationDate()),
                KEY_FIELDS.split(",")[0], entity.getId()));
        this.deleteArticleItemsByShoppingCartId(entity.getId());
        this.createRelations(entity.getId(), entity.getArticleItems());
        return this.read(entity.getId())
                .orElseThrow();
    }

    @Override
    public Optional<ShoppingCart> read(Integer id) {
        Optional<ShoppingCart> shoppingCart = this.executeQueryConvert(String.format("SELECT %s FROM %s WHERE id = %d", KEY_FIELDS, TABLE, id)).stream()
                .findFirst();
        if (shoppingCart.isPresent()) {
            this.readArticleItems(shoppingCart.get());
        }
        return shoppingCart;
    }

    private void readArticleItems(ShoppingCart shoppingCart) {
        ResultSet resultSet = this.executeQuery(String.format(
                "SELECT article_id,amount,discount FROM %s WHERE shoppingCart_id = %d", RELATION, shoppingCart.getId()));
        try {
            while (resultSet.next()) {
                shoppingCart.add(new ArticleItem(this.articleRepositoryMysql.read(resultSet.getInt("article_id")).orElseThrow(),
                        resultSet.getInt("amount"), resultSet.getBigDecimal("discount")));
            }
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Read article " + TABLE + " error: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        this.deleteArticleItemsByShoppingCartId(id);
        this.executeUpdate(String.format("DELETE FROM %s WHERE id = %d", TABLE, id));
    }

    private void deleteArticleItemsByShoppingCartId(Integer shoppingCartId) {
        this.executeUpdate(String.format("DELETE FROM %s WHERE shoppingCart_id = %d", RELATION, shoppingCartId));
    }

    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> shoppingCarts = this.executeQueryConvert(String.format("SELECT %s FROM %s", KEY_FIELDS, TABLE));
        for (ShoppingCart shoppingCart : shoppingCarts) {
            this.readArticleItems(shoppingCart);
        }
        return shoppingCarts;
    }

    @Override
    protected ShoppingCart convertToEntity(ResultSet resultSet) {
        try {
            return new ShoppingCart(
                    resultSet.getInt(KEY_FIELDS.split(",")[0]),
                    this.userRepositoryMysql.read(resultSet.getInt(KEY_FIELDS.split(",")[1])).orElseThrow(),
                    resultSet.getTimestamp(KEY_FIELDS.split(",")[2]).toLocalDateTime());
        } catch (SQLException e) {
            throw new RuntimeException("convert To ShoppingCart" + TABLE + " error: " + e.getMessage());
        }

    }
}
