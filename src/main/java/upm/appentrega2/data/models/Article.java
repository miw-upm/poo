package upm.appentrega2.data.models;

import java.time.LocalDate;
import java.util.Objects;

public class Article {
    private static final int EUROPEAN_ARTICLE_NUMBER = 13;
    private Integer id;
    private String barcode;
    private String summary;
    private Double price;
    private LocalDate registrationDate;
    private String provider;

    public Article(String barcode, String summary, Double price, String provider) {
        this.setBarcode(barcode);
        this.summary = summary;
        this.price = price;
        this.provider = provider;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        if (barcode.length() != EUROPEAN_ARTICLE_NUMBER) {
            throw new RuntimeException("La longitud del código de barras para un artículo es incorrecto: " + barcode);
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

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
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
}
