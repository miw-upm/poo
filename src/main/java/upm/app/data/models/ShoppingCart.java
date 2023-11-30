package upm.app.data.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {
    private Integer id;
    private final User user;
    private final LocalDateTime creationDate;
    private List<ArticleItem> articleItems;


    public ShoppingCart(User user, LocalDateTime creationDate) {
        this(null,user,creationDate);
    }

    public ShoppingCart(Integer id, User user, LocalDateTime creationDate) {
        this.id = id;
        this.user = user;
        this.creationDate = creationDate;
        this.articleItems = new ArrayList<>();
    }

    public BigDecimal total() {
        BigDecimal total = BigDecimal.ZERO;
        for (ArticleItem articleItem : this.articleItems) {
            total = total.add(articleItem.totalUnit());
        }
        return total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public List<ArticleItem> getArticleItems() {
        return articleItems;
    }

    public void setArticleItems(List<ArticleItem> articleItems) {
        this.articleItems = articleItems;
    }

    public void add(ArticleItem articleItem) {
        this.articleItems.add(articleItem);
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", user=" + user +
                ", creationDate=" + creationDate +
                ", articleItems=" + articleItems +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object shoppingCart) {
        return this == shoppingCart || shoppingCart != null && getClass() == shoppingCart.getClass() && (this.id.equals(((ShoppingCart) shoppingCart).id));
    }
}
