package upm.appentrega2.services;

import upm.appentrega2.data.models.Article;
import upm.appentrega2.data.models.Tag;
import upm.appentrega2.data.repositories.ArticleRepository;
import upm.appentrega2.data.repositories.TagRepository;
import upm.appentrega2.services.exceptions.DuplicateException;
import upm.appentrega2.services.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public class ArticleService {
    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;

    public ArticleService(ArticleRepository articleRepository, TagRepository tagRepository) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
    }

    public Article create(Article article) {
        if (this.articleRepository.findByBarcode(article.getBarcode()).isPresent()) {
            throw new DuplicateException("El codigo de barras ya existe, y debiera ser único: " + article.getBarcode());
        }
        return this.articleRepository.create(article);
    }

    public List<Article> findByTagName(String tagName) {
        Optional<Tag> tag = this.tagRepository.findByName(tagName);
        if (tag.isEmpty()){
            return List.of();
        }
        return tag.get().getArticles();
    }

    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }
}