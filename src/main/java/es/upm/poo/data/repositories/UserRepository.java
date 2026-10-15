package es.upm.poo.data.repositories;

import es.upm.poo.data.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
    Optional<User> findByEmail(String email);
}