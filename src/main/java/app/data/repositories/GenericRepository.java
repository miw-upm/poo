package app.data.repositories;

public interface GenericRepository<T> {
    T create(T entity);
}