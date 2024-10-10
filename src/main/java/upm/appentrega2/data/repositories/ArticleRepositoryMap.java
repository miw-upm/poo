package upm.appentrega2.data.repositories;

import upm.appentrega2.data.models.Article;

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

    public Article update(Article entity) {
        if (this.map.containsKey(entity.getId())) {
            this.map.put(entity.getId(), entity);
        }
        return entity;
    }

    public Optional<Article> read(Integer id) {
        return Optional.ofNullable(this.map.get(id));
    }

    public void deleteById(Integer id) {
        this.map.remove(id);
    }

    public Optional<Article> findByBarcode(String barcode) {
        for (Article article : this.findAll()) {
            if (barcode.equals(article.getBarcode())) {
                return Optional.of(article);
            }
        }
        return Optional.empty();
    }

    public List<Article> findAll() {
        return new ArrayList<>(map.values());
    }

}
