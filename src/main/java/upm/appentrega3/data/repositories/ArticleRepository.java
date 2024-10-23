package upm.appentrega3.data.repositories;

import upm.appentrega3.data.models.Article;

import java.util.Optional;

public interface ArticleRepository extends GenericRepository<Article> {
    Optional<Article> findByBarcode(String barcode);
}
