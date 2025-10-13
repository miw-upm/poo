package upm.app2.data.repositories;

import upm.app2.data.models.Article;

import java.util.Optional;

public interface ArticleRepository extends GenericRepository<Article> {
    Optional<Article> findByBarcode(String barcode);
}
