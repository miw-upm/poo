package upm.app2.data.repositories.map;

import upm.app2.data.models.User;
import upm.app2.data.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
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

    public List<User> findByName(String name) {
        List<User> result = new ArrayList<>();
        for (User user : this.findAll()) {
            if (user.getName() != null && user.getName().equalsIgnoreCase(name)) {
                result.add(user);
            }
        }
        return result;
    }
}