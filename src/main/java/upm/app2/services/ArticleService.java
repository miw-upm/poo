package upm.app2.services;

import upm.app2.data.models.Article;
import upm.app2.data.repositories.ArticleRepository;

import java.util.stream.Stream;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public Stream<Article> findAll() {
        return this.articleRepository.findAll().stream();
    }
}
