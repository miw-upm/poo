package upm.appentrega3.services;

import upm.appentrega3.data.models.Article;
import upm.appentrega3.data.models.Tag;
import upm.appentrega3.data.repositories.ArticleRepository;
import upm.appentrega3.data.repositories.TagRepository;
import upm.appentrega3.services.exceptions.DuplicateException;
import upm.appentrega3.services.exceptions.NotFoundException;

import java.util.stream.Stream;

public class TagService {
    private final TagRepository tagRepository;
    ArticleRepository articleRepository;

    public TagService(TagRepository tagRepository, ArticleRepository articleRepository) {
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
    }

    public Tag create(Tag tag) {
        this.tagRepository.findByName(tag.getName()).ifPresent(existingTag -> {
            throw new DuplicateException(String.format("El nombre de etiqueta '%s' ya existe y debe ser único.", tag.getName()));
        });
        return this.tagRepository.create(tag);
    }

    public Tag addArticle(String name, String articleBarcode) {
        Tag dbTag = this.tagRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Nombre de etiqueta no encontrado: " + name));
        Article dbArticle = this.articleRepository.findByBarcode(articleBarcode)
                .orElseThrow(() -> new NotFoundException("Codigo de barras no encontrado: " + articleBarcode));
        dbTag.addArticle(dbArticle);
        this.tagRepository.update(dbTag);
        return dbTag;
    }

    public Stream<Tag> findByArticleBarcode(String articleBarcode) {
        return this.tagRepository.findAll().stream()
                .filter(tag -> tag.getArticles().stream()
                        .anyMatch(article -> articleBarcode.equals(article.getBarcode())));
    }
}