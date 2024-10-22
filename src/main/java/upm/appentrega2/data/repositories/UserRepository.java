package upm.appentrega2.data.repositories;

import upm.appentrega2.data.models.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
    Optional<User> findByMobile(Integer mobile);
}