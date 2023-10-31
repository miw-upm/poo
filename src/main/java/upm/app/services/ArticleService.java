package upm.app.services;

import upm.app.data.models.Article;
import upm.app.data.models.Tag;
import upm.app.data.repositories.TagRepository;

import java.util.List;

public class ArticleService {
    private final TagRepository tagRepository;

    public ArticleService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Article> findByTagName(String tagName) {
        Tag tag = this.tagRepository.findByName(tagName).orElseThrow(() -> new IllegalArgumentException("Nombre de Etiqueta no encontrado: " + tagName));
        return tag.getArticles();
    }
}
