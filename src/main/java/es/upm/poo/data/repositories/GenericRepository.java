package es.upm.poo.data.repositories;

import java.util.Optional;

public interface GenericRepository<T> {
    T create(T entity);

    Optional<T> read(Long id);
}
