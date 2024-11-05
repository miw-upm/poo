package upm.appentrega2.services;

import upm.appentrega2.data.models.Article;
import upm.appentrega2.data.models.Tag;
import upm.appentrega2.data.repositories.ArticleRepository;
import upm.appentrega2.data.repositories.TagRepository;
import upm.appentrega2.services.exceptions.DuplicateException;
import upm.appentrega2.services.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TagService {
    private final TagRepository tagRepository;
    ArticleRepository articleRepository;

    public TagService(TagRepository tagRepository, ArticleRepository articleRepository) {
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
    }

    public Tag create(Tag tag) {
        if (this.tagRepository.findByName(tag.getName()).isPresent()) {
            throw new DuplicateException("El nombre de etiqueta ya existe, y debiera ser único: " + tag.getName());
        }
        return this.tagRepository.create(tag);
    }

    public Tag addArticle(String name, String articleBarcode) {
        Optional<Tag> dbTag = this.tagRepository.findByName(name);
        if (dbTag.isEmpty()) {
            throw new NotFoundException("Nombre de etiqueta no encontrado: " + name);
        }
        Optional<Article> dbArticle = this.articleRepository.findByBarcode(articleBarcode);
        if (dbArticle.isEmpty()) {
            throw new NotFoundException("Codigo de barras no encontrado: " + articleBarcode);
        }
        dbTag.get().addArticle(dbArticle.get());
        this.tagRepository.update(dbTag.get());
        return dbTag.get();
    }

    public List<Tag> findByArticleBarcode(String articleBarcode) {
        List<Tag> tagsResult = new ArrayList<>();
        List<Tag> tags = this.tagRepository.findAll();
        for (Tag tag : tags) {
            for (Article article : tag.getArticles()) {
                if (articleBarcode.equals(article.getBarcode())) {
                    tagsResult.add(tag);
                    break;
                }
            }
        }
        return tagsResult;
    }
}