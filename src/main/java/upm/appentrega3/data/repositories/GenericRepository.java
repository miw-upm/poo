package upm.appentrega3.data.repositories;

import upm.appentrega3.data.models.Entity;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T extends Entity> {
    T create(T entity);

    T update(T entity);

    Optional<T> read(Integer id);

    void deleteById(Integer id);

    List<T> findAll();
}