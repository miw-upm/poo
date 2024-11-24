package upm.appentrega4.services;

import upm.appentrega4.data.models.Article;
import upm.appentrega4.data.models.CreationTag;
import upm.appentrega4.data.models.Tag;
import upm.appentrega4.data.repositories.ArticleRepository;
import upm.appentrega4.data.repositories.TagRepository;
import upm.appentrega4.services.exceptions.DuplicateException;
import upm.appentrega4.services.exceptions.NotFoundException;

import java.util.stream.Stream;

public class TagService {
    private final TagRepository tagRepository;
    ArticleRepository articleRepository;

    public TagService(TagRepository tagRepository, ArticleRepository articleRepository) {
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
    }

    public Tag create(CreationTag creationTag) {
        this.tagRepository.findByName(creationTag.name()).ifPresent(existingTag -> {
            throw new DuplicateException(String.format("El nombre de etiqueta '%s' ya existe y debe ser único.", creationTag.name()));
        });
        Article dbArticle = this.articleRepository.findByBarcode(creationTag.barcode())
                .orElseThrow(() -> new NotFoundException("Codigo de barras no encontrado: " + creationTag.barcode()));
        Tag tag = new Tag(creationTag.name(), creationTag.description());
        tag.addArticle(dbArticle);
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