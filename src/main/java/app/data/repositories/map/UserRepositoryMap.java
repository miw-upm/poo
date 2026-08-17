package app.data.repositories.map;

import app.data.models.User;
import app.data.repositories.UserRepository;

import java.util.List;

public class UserRepositoryMap extends GenericRepositoryMap<User> implements UserRepository {

    @Override
    public void setId(User entity, long id) {
       entity.setId(id);
    }

    @Override
    public List<User> findByEmail(String email) {
        return this.findAll().stream()
                .filter(user -> email != null && email.equals(user.getEmail()))
                .toList();
    }
}
