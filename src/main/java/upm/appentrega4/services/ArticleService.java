package upm.appentrega4.services;

import upm.appentrega4.data.models.Article;
import upm.appentrega4.data.repositories.ArticleRepository;
import upm.appentrega4.data.repositories.TagRepository;
import upm.appentrega4.services.exceptions.DuplicateException;

import java.util.stream.Stream;

public class ArticleService {
    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    public ArticleService(ArticleRepository articleRepository, TagRepository tagRepository) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
    }

    public Article create(Article article) {
        this.articleRepository.findByBarcode(article.getBarcode())
                .ifPresent(article1 -> {
                    throw new DuplicateException("El codigo de barras ya existe, y debiera ser único: " + article1.getBarcode());
                });
        return this.articleRepository.create(article);
    }

    public Stream<Article> findByTagName(String tagName) {
        return this.tagRepository.findByName(tagName).stream()
                .flatMap(tag -> tag.getArticles().stream())
                .distinct();
    }

    public Stream<Article> findAll() {
        return this.articleRepository.findAll().stream();
    }
}