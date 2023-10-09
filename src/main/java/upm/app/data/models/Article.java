package upm.app.data.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Article {
    private Integer id;
    private String barcode;
    private String summary;
    private BigDecimal price;
    private final LocalDate registrationDate;
    private String provider;

    public Article(String barcode, String summary, BigDecimal price, LocalDate registrationDate, String provider) {
        this.barcode = barcode;
        this.summary = summary;
        this.price = price;
        this.registrationDate = registrationDate;
        this.provider = provider;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
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
        return this == article || article != null && getClass() == article.getClass() && (this.equals(((Article) article).id));
    }
}
