package upm.appentrega4.data.repositories;

import upm.appentrega4.data.models.Article;

import java.util.Optional;

public interface ArticleRepository extends GenericRepository<Article> {
    Optional<Article> findByBarcode(String barcode);
}
