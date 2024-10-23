package upm.appentrega3.data.repositories;

import upm.appentrega3.data.models.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
    Optional<User> findByMobile(Integer mobile);
}