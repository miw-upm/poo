package upm.appentrega4.data.repositories.map;

import upm.appentrega4.data.models.User;
import upm.appentrega4.data.repositories.UserRepository;

import java.util.Optional;

public class UserRepositoryMap extends GenericRepositoryMap<User> implements UserRepository {

    @Override
    public Optional<User> findByMobile(Integer mobile) {
        for (User user : this.findAll()) {
            if (user.getMobile().equals(mobile)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}