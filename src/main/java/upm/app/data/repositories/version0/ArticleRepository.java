package upm.app.data.repositories.version0;

import upm.app.data.models.Article;

import java.util.*;

public class ArticleRepository {

    private final Map<Integer, Article> map;
    private int id;

    public ArticleRepository() {
        this.map = new HashMap<>();
        this.id = 1;
    }

    public Article create(Article entity) {
        entity.setId(this.id++);
        this.map.put(id, entity);
        return entity;
    }

    public Article update(Article entity) {
        if (this.map.containsKey(entity.getId())) {
            this.map.put(entity.getId(), entity);
        }
        return entity;
    }

    public Optional<Article> read(Integer id) {
        return Optional.of(this.map.get(id));
    }

    public void deleteById(Integer id) {
        this.map.remove(id);
    }

    public List<Article> findAll() {
        return new ArrayList<>(map.values());
    }

}
