package upm.appentrega3.services;

import upm.appentrega3.data.models.Article;
import upm.appentrega3.data.models.Tag;
import upm.appentrega3.data.repositories.ArticleRepository;
import upm.appentrega3.data.repositories.TagRepository;
import upm.appentrega3.services.exceptions.DuplicateException;
import upm.appentrega3.services.exceptions.NotFoundException;

import java.util.List;

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
        Tag tag = this.tagRepository.findByName(tagName).orElseThrow(() -> new NotFoundException("Nombre de Etiqueta no encontrado: " + tagName));
        return tag.getArticles();
    }

    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }
}