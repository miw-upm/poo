package upm.app.data.repositories.repositories_map;


import upm.app.data.models.Article;
import upm.app.data.models.User;
import upm.app.data.repositories.UserRepository;

import java.util.Optional;

public class UserRepositoryMap extends GenericRepositoryMap<User> implements UserRepository {

    @Override
    protected Integer getId(User entity) {
        return entity.getId();
    }

    @Override
    protected void setId(User entity, Integer id) {
        entity.setId(id);
    }

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
