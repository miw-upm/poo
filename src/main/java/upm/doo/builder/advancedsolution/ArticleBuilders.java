package upm.doo.builder.advancedsolution;

import java.math.BigDecimal;

public interface ArticleBuilders {

    interface Barcode {
        Summary barcode(String barcode);
    }

    interface Summary {
        Price summary(String sumary);
    }

    interface Price {
        Optionals price(BigDecimal price);
    }

    interface Optionals {
        Optionals provider(String provider);

        Article build();

    }
}
