package upm.app.services;

import upm.app.data.models.Article;
import upm.app.data.models.Tag;
import upm.app.data.repositories.ArticleRepository;
import upm.app.data.repositories.TagRepository;
import upm.app.services.exceptions.DuplicateException;
import upm.app.services.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public class ArticleService {
    private final TagRepository tagRepository;
    private final ArticleRepository articleRepository;


    public ArticleService(ArticleRepository articleRepository, TagRepository tagRepository) {
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
    }

    public List<Article> findByTagName(String tagName) {
        Tag tag = this.tagRepository.findByName(tagName).orElseThrow(() -> new NotFoundException("Nombre de Etiqueta no encontrado: " + tagName));
        return tag.getArticles();
    }

    public Article create(Article article) {
        if (this.articleRepository.findByBarcode(article.getBarcode()).isPresent()) {
            throw new DuplicateException("El Código de barras ya existe, y debiera ser único: " + article.getBarcode());
        }
        article.setRegistrationDate(LocalDate.now());
        return this.articleRepository.create(article);
    }
}
