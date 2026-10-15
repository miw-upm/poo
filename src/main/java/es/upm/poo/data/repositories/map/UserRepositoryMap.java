package es.upm.poo.data.repositories.map;

import es.upm.poo.data.model.User;
import es.upm.poo.data.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserRepositoryMap extends GenericRepositoryMap<User> implements UserRepository {

    @Override
    public void setId(User entity, long id) {
        entity.setId(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (email == null) {
            return Optional.empty();
        }
        return this.findAll().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst();
    }
}
