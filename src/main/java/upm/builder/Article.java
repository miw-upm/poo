package upm.builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Article {

    private static final int EUROPEAN_ARTICLE_NUMBER = 13;
    private LocalDate registrationDate;
    private Integer id;
    private String barcode;
    private String summary;
    private BigDecimal price;
    private String provider;

    public static Builder builder() {
        return new Builder();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        if (barcode.length() != EUROPEAN_ARTICLE_NUMBER) {
            throw new IllegalArgumentException("La longitud del código de barras para un artículo es incorrecto: " + barcode);
        }
        this.barcode = barcode;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", summary='" + summary + '\'' +
                ", price=" + price +
                ", registrationDate=" + registrationDate +
                ", provider='" + provider + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object article) {
        return this == article || article != null && getClass() == article.getClass() && (this.id.equals(((Article) article).id));
    }

    public static class Builder {
        private final Article article;

        public Builder() {
            this.article = new Article();
        }

        public Builder barcode(String barcode) {
            this.article.setBarcode(barcode);
            return this;
        }

        public Builder summary(String summary) {
            this.article.summary = summary;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.article.price = price;
            return this;
        }

        public Builder provider(String provider) {
            this.article.provider = provider;
            return this;
        }

        public Article build() {
            return this.article;
        }

    }

}
