package upm.appentrega2.data.repositories.map;

import upm.appentrega2.data.models.Article;
import upm.appentrega2.data.repositories.ArticleRepository;

import java.util.Optional;

public class ArticleRepositoryMap extends GenericRepositoryMap<Article> implements ArticleRepository {

    @Override
    protected Integer getId(Article entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Article entity, Integer id) {
        entity.setId(id);
    }

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
