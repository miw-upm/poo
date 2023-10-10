package upm.app.data.repositories.version1;

import upm.app.data.models.Article;

import java.util.Optional;

public class ArticleRepositoryMap extends GenericRepositoryMap<Article> {
    @Override
    protected Integer getId(Article entity) {
        return entity.getId();
    }

    @Override
    protected void setId(Article entity, Integer id) {
        entity.setId(id);
    }

    public Optional<Article> findByBarcode(String barcode) {
        for (Article article : this.findAll()) {
            if (article.getBarcode().equals(barcode)) {
                return Optional.of(article);
            }
        }
        return Optional.empty();
    }
}
