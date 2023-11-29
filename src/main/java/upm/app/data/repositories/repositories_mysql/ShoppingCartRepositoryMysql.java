package upm.app.data.repositories.repositories_mysql;

import upm.app.data.models.Article;
import upm.app.data.models.ShoppingCart;
import upm.app.data.models.Tag;
import upm.app.data.models.User;
import upm.app.data.repositories.ShoppingCartRepository;
import upm.app.data.repositories.TagRepository;
import upm.app.data.repositories.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ShoppingCartRepositoryMysql extends GenericRepositoryMysql<ShoppingCart> implements ShoppingCartRepository {
    private static final String TABLE = "shoppingCart";
    private static final String RELATION = "article_item";
    private static final String FIELDS = "user_id,creationDate";
    private static final String KEY_FIELDS = "id," + FIELDS;
    private final UserRepositoryMysql userRepositoryMysql;


    public ShoppingCartRepositoryMysql(UserRepositoryMysql userRepositoryMysql) {
        this.userRepositoryMysql = userRepositoryMysql;
        this.initializeTable();
    }

    private void initializeTable() {
        this.executeUpdate("CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                KEY_FIELDS.split(",")[0] + " SERIAL PRIMARY KEY," + //id auto increment
                KEY_FIELDS.split(",")[1] + " VARCHAR(20) UNIQUE NOT NULL," +
                KEY_FIELDS.split(",")[2] + " VARCHAR(20))");

        this.executeUpdate("CREATE TABLE IF NOT EXISTS " + RELATION + "(" +
                "id SERIAL PRIMARY KEY," +
                "shoppingCart_id BIGINT UNSIGNED," +
                "article_id BIGINT UNSIGNED," +
                "amount INT" +
                "discount DECIMAL" +
                "FOREIGN KEY (shoppingCart_id) REFERENCES shoppingCart(id)," +
                "FOREIGN KEY (article_id) REFERENCES article(id) )");
    }


    @Override
    public ShoppingCart create(ShoppingCart entity) {
        return null;
    }

    @Override
    public ShoppingCart update(ShoppingCart entity) {
        return null;
    }

    @Override
    public Optional<ShoppingCart> read(Integer id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<ShoppingCart> findAll() {
        return null;
    }

    @Override
    protected ShoppingCart convertToEntity(ResultSet resulSet) {
        return null;
    }
}
