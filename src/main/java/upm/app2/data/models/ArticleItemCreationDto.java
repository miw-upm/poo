package upm.app2.data.models;

import upm.app2.data.models.exceptions.InvalidAttributeException;

import java.math.BigDecimal;

// Implemented: getters, toString, equals & hashCode
public record ArticleItemCreationDto(
        Integer articleId,
        Integer amount,
        BigDecimal discount) {
    public ArticleItemCreationDto {
        if (amount <= 0) {
            throw new InvalidAttributeException("La cantidad no puede ser negativa o cero: " + amount);
        }
    }
}

