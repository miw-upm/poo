package es.upm.poo.data.repositories;

public interface GenericRepository<T> {
    T create(T entity);
}