package upm.appentrega3.services;

import upm.appentrega3.data.models.Article;
import upm.appentrega3.data.models.Tag;
import upm.appentrega3.data.repositories.ArticleRepository;
import upm.appentrega3.data.repositories.TagRepository;
import upm.appentrega3.services.exceptions.DuplicateException;

import java.util.List;
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
                    throw new DuplicateException("El codigo de barras ya existe, y debiera ser único: " + article.getBarcode());
                });
        return this.articleRepository.create(article);
    }

    public Stream<Article> findByTagName(String tagName) {
        return this.tagRepository.findByName(tagName).stream()
                .flatMap(tag -> tag.getArticles().stream());
    }

    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }
}