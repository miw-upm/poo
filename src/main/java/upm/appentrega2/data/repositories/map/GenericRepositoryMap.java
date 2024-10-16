package upm.appentrega2.data.repositories.map;

import upm.appentrega2.data.models.Entity;
import upm.appentrega2.data.repositories.GenericRepository;

import java.util.*;

public abstract class GenericRepositoryMap<T extends Entity> implements GenericRepository<T> {

    private final Map<Integer, T> map;

    private int id;

    GenericRepositoryMap() {
        this.map = new HashMap<>();
        this.id = 1;
    }

    @Override
    public T create(T entity) {
        entity.setId(this.id);
        this.map.put(this.id, entity);
        this.id++;
        return entity;
    }

    @Override
    public T update(T entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("No se puede actualizar una entidad cuando su id es null: " + entity);
        }
        this.map.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> read(Integer id) {
        return Optional.ofNullable(this.map.get(id));
    }

    @Override
    public void deleteById(Integer id) {
        this.map.remove(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(map.values());
    }

}
