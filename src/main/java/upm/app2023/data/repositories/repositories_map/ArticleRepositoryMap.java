package upm.app2023.data.repositories.repositories_map;

import upm.app2023.data.models.Article;
import upm.app2023.data.repositories.ArticleRepository;

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
            if (article.getBarcode().equals(barcode)) {
                return Optional.of(article);
            }
        }
        return Optional.empty();
    }
}
