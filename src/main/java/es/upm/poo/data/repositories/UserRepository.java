package es.upm.poo.data.repositories;

import es.upm.poo.data.model.User;

import java.util.List;

public interface UserRepository extends GenericRepository<User> {
    List<User> findByEmail(String email);
}