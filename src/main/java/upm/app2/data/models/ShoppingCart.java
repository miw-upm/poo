package upm.app2.data.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends Entity {
    private User user;
    private LocalDateTime creationDate;
    private List<ArticleItem> articleItems;

    public ShoppingCart(User user, LocalDateTime creationDate) {
        this.user = user;
        this.creationDate = creationDate;
        this.articleItems = new ArrayList<>();
    }

    public BigDecimal total() {
        BigDecimal total = BigDecimal.ZERO;
        for (ArticleItem articleItem : this.articleItems) {
            total = total.add(articleItem.total());
        }
        return total;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<ArticleItem> getArticleItems() {
        return this.articleItems;
    }

    public void setArticleItems(List<ArticleItem> articleItems) {
        this.articleItems = articleItems;
    }

    public void add(ArticleItem articleItem) {
        this.articleItems.add(articleItem);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "user=" + user +
                ", creationDate=" + creationDate +
                ", articleItems=" + articleItems +
                "} " + super.toString();
    }
}