package upm.app.data.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericRepository<T> {

    private final Map<Integer, T> map;

    private int id;

    GenericRepository() {
        this.map = new HashMap<>();
        this.id = 1;
    }

    public void create(T entity) {
        this.setId(entity, this.id++);
        this.map.put(id, entity);
    }

    public void update(T entity) {
        if (this.map.containsKey(this.getId(entity))) {
            this.map.put(this.getId(entity), entity);
        }
    }

    public T read(Integer id) {
        return this.map.get(id);
    }


    public void deleteById(Integer id) {
        this.map.remove(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(map.values());
    }

    public abstract Integer getId(T entity);

    public abstract void setId(T entity, Integer id);

}
