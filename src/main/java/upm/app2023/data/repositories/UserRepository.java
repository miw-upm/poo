package upm.app2023.data.repositories;

import upm.app2023.data.models.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
    Optional<User> findByMobile(Integer mobile);
}
