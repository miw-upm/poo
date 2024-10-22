package upm.appentrega2.data.repositories.map;

import upm.appentrega2.data.models.Article;
import upm.appentrega2.data.repositories.ArticleRepository;

import java.util.Optional;

public class ArticleRepositoryMap extends GenericRepositoryMap<Article> implements ArticleRepository {

    @Override
    public Optional<Article> findByBarcode(String barcode) {
        for (Article article : this.findAll()) {
            if (barcode.equals(article.getBarcode())) {
                return Optional.of(article);
            }
        }
        return Optional.empty();
    }
}