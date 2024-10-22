package upm.appentrega2.services;

import upm.appentrega2.data.models.Article;
import upm.appentrega2.data.repositories.ArticleRepository;
import upm.appentrega2.services.exceptions.DuplicateException;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article create(Article article) {
        if (this.articleRepository.findByBarcode(article.getBarcode()).isPresent()) {
            throw new DuplicateException("El codigo de barras ya existe, y debiera ser único: " + article.getBarcode());
        }
        return this.articleRepository.create(article);
    }

    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }
}
