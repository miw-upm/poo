package upm.app.data.repositories.version1;

import java.util.*;

public abstract class GenericRepositoryMap<T> {

    private final Map<Integer, T> map;

    private int id;

    GenericRepositoryMap() {
        this.map = new HashMap<>();
        this.id = 1;
    }

    public T create(T entity) {
        this.setId(entity, this.id);
        this.map.put(this.id, entity);
        this.id++;
        return entity;
    }

    public T update(T entity) {
        if (this.getId(entity) == null) {
            throw new IllegalArgumentException("No se puede actualizar una entidad cuando su id es null: " + entity);
        }
        this.map.put(this.getId(entity), entity);
        return entity;
    }

    public Optional<T> read(Integer id) {
        return Optional.of(this.map.get(id));
    }

    public void deleteById(Integer id) {
        this.map.remove(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(map.values());
    }

    protected abstract Integer getId(T entity);

    protected abstract void setId(T entity, Integer id);

}
