package upm.appentrega3.services;

import upm.appentrega3.data.models.Article;
import upm.appentrega3.data.models.Tag;
import upm.appentrega3.data.repositories.ArticleRepository;
import upm.appentrega3.data.repositories.TagRepository;
import upm.appentrega3.services.exceptions.DuplicateException;
import upm.appentrega3.services.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

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
        Tag dbTag = this.tagRepository.findByName(name).orElseThrow(() -> new NotFoundException("Nombre de etiqueta no encontrado: " + name));
        Article dbArticle = this.articleRepository.findByBarcode(articleBarcode).orElseThrow(() -> new NotFoundException("Codigo de barras no encontrado: " + articleBarcode));
        dbTag.addArticle(dbArticle);
        this.tagRepository.update(dbTag);
        return dbTag;
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