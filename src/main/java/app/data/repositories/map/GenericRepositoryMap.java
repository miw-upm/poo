package app.data.repositories.map;


import app.data.repositories.GenericRepository;

import java.util.*;

public abstract class GenericRepositoryMap<T> implements GenericRepository<T> {

    private final Map<Long, T> map;
    private long id;

    GenericRepositoryMap() {
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

    public abstract void setId(T entity, long id);
}