package upm.app.data.repositories;

import upm.app.data.models.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
    Optional<User> findByMobile(Integer mobile);
}
