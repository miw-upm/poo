package upm.app2.data.repositories;

import upm.app2.data.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
    Optional<User> findByMobile(Integer mobile);
    List<User> findByName(String name);
}