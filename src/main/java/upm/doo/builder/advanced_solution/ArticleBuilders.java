package upm.doo.builder.advanced_solution;

import java.math.BigDecimal;

public interface ArticleBuilders {

    interface Barcode {
        Summary barcode(String barcode);
    }

    interface Summary {
        Price summary(String nick);
    }

    interface Price {
        Optionals price(BigDecimal price);
    }

    interface Optionals {
        Optionals provider(String provider);

        Article build();

    }
}
