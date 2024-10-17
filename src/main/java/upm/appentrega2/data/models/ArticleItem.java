package upm.appentrega2.data.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArticleItem {
    private Article article;
    private Integer amount;
    private BigDecimal discount;

    public ArticleItem(Article article, Integer amount, BigDecimal discount) {
        this.article = article;
        this.amount = amount;
        this.discount = discount;
    }

    public BigDecimal totalUnit() {
        this.discount = discount.setScale(6, RoundingMode.HALF_UP);
        BigDecimal totalUnit = this.article.getPrice().multiply(
                BigDecimal.ONE.subtract(this.discount.divide(new BigDecimal("100"), RoundingMode.HALF_UP)));
        return totalUnit.setScale(2, RoundingMode.HALF_UP);
    }

    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ArticleItem{" +
                "article=" + article +
                ", amount=" + amount +
                ", discount=" + discount +
                '}';
    }
}
