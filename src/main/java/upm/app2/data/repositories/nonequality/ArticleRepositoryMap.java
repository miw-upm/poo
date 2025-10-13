package upm.app2.data.repositories.nonequality;

import upm.app2.data.models.Article;

import java.util.*;

public class ArticleRepositoryMap {

    private final Map<Integer, Article> map;
    private int id;

    public ArticleRepositoryMap() {
        this.map = new HashMap<>();
        this.id = 1;
    }

    public Article create(Article entity) {
        entity.setId(this.id);
        this.map.put(id, entity);
        this.id++;
        return entity;
    }

    public Article update(Integer id, Article entity) {
        if (!this.map.containsKey(id)) {
            throw new RuntimeException("El id no existe: " + id);
        }
        this.map.put(entity.getId(), entity);
        return entity;
    }

    public Optional<Article> read(Integer id) {
        return Optional.ofNullable(this.map.get(id));
    }

    public void deleteById(Integer id) {
        this.map.remove(id);
    }

    public List<Article> findAll() {
        return new ArrayList<>(map.values());
    }

}
