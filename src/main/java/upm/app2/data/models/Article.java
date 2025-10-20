package upm.app2.data.models;

import upm.app2.data.models.exceptions.InvalidAttributeException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Article extends Entity {
    private static final int EUROPEAN_ARTICLE_NUMBER = 13;
    private String barcode;
    private String summary;
    private BigDecimal price;
    private LocalDate registrationDate;
    private String provider;

    public Article(String barcode, String summary, BigDecimal price, String provider) {
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
            throw new InvalidAttributeException("La longitud del código de barras debe ser: " + EUROPEAN_ARTICLE_NUMBER + ", encontrado:" + barcode);
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

    @Override
    public String toString() {
        return "Article{" +
                "barcode='" + barcode + '\'' +
                ", summary='" + summary + '\'' +
                ", price=" + price +
                ", registrationDate=" + registrationDate +
                ", provider='" + provider + '\'' +
                "} " + super.toString();
    }
}
