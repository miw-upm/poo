package upm.app.data.repositories.version0;

import upm.app.data.models.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleRepository {

    private final Map<Integer, Article> map;
    private int id;

    public ArticleRepository() {
        this.map = new HashMap<>();
        this.id = 1;
    }

    public void create(Article entity) {
        entity.setId(this.id++);
        this.map.put(id, entity);
    }

    public void update(Article entity) {
        if (this.map.containsKey(entity.getId())) {
            this.map.put(entity.getId(), entity);
        }
    }

    public Article read(Integer id) {
        return this.map.get(id);
    }

    public void deleteById(Integer id) {
        this.map.remove(id);
    }

    public List<Article> findAll() {
        return new ArrayList<>(map.values());
    }

}
