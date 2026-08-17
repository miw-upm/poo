package app.data.repositories;

import app.data.models.User;

import java.util.List;

public interface UserRepository extends GenericRepository<User> {
    List<User> findByEmail(String email);
}