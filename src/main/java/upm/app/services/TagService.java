package upm.app.services;

import upm.app.data.models.Article;
import upm.app.data.models.Tag;
import upm.app.data.repositories.ArticleRepository;
import upm.app.data.repositories.TagRepository;

public class TagService {
    private TagRepository tagRepository;
    private ArticleRepository articleRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag create(Tag tag){
        if (this.tagRepository.findByName(tag.getName()).isPresent()){
            throw new IllegalArgumentException("El nombre de la Etiqueta ya existe, y debiera ser único: " + tag.getName());
        }
        return this.tagRepository.create(tag);
    }

    public void addArticle(Integer tagId, Integer articleId){
        Tag tag = this.tagRepository.read(tagId).orElseThrow(()->new IllegalArgumentException("El tagId no existe: " + tagId) );
        Article article = this.articleRepository.read(articleId).orElseThrow(()->new IllegalArgumentException("El articleId no existe: " + articleId) );
        tag.addArticle(article);
        this.tagRepository.update(tag);
    }
}
