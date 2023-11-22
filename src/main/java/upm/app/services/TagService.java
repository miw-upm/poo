package upm.app.services;

import upm.app.data.models.Article;
import upm.app.data.models.Tag;
import upm.app.data.repositories.ArticleRepository;
import upm.app.data.repositories.TagRepository;

import java.util.ArrayList;
import java.util.List;

public class TagService {
    private final TagRepository tagRepository;
    private final ArticleRepository articleRepository;

    public TagService(TagRepository tagRepository, ArticleRepository articleRepository) {
        this.tagRepository = tagRepository;
        this.articleRepository = articleRepository;
    }

    public Tag create(Tag tag) {
        if (this.tagRepository.findByName(tag.getName()).isPresent()) {
            throw new IllegalArgumentException("El nombre de la Etiqueta ya existe, y debiera ser único: " + tag.getName());
        }
        return this.tagRepository.create(tag);
    }

    public void addArticle(Integer tagId, Integer articleId) {
        Tag tag = this.tagRepository.read(tagId).orElseThrow(() -> new IllegalArgumentException("El tagId no existe: " + tagId));
        Article article = this.articleRepository.read(articleId).orElseThrow(() -> new IllegalArgumentException("El articleId no existe: " + articleId));
        tag.addArticle(article);
        this.tagRepository.update(tag);
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

    public List<Tag> findByArticleBarcodeFunctional(String articleBarcode) {
        return this.tagRepository.findAll().stream()
                .filter(tag -> tag.getArticles().stream()
                        .anyMatch(article -> articleBarcode.equals(article.getBarcode())))
                .toList();
    }
}
