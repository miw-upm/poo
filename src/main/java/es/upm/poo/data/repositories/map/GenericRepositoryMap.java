package es.upm.poo.data.repositories.map;


import es.upm.poo.data.repositories.GenericRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class GenericRepositoryMap<T> implements GenericRepository<T> {

    private final Map<Long, T> map;
    private long id;

    protected GenericRepositoryMap() {
        this.map = new HashMap<>();
        this.id = 1;
    }

    @Override
    public T create(T entity) {
        this.setId(entity, this.id);
        this.map.put(this.id, entity);
        this.id++;
        return entity;
    }

    @Override
    public Optional<T> read(Long id) {
        return Optional.ofNullable(this.map.get(id));
    }

    @Override
    public T update(Long id, T entity) {
        this.setId(entity, id);
        this.map.put(id, entity);
        return entity;
    }

    @Override
    public List<T> findAll() {
        return this.map.values().stream().toList();
    }

    public abstract void setId(T entity, long id);
}
