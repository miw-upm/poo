package upm.appentrega4.data.repositories;

import upm.appentrega4.data.models.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
    Optional<User> findByMobile(Integer mobile);
}