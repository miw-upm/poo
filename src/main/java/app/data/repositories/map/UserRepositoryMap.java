package app.data.repositories.map;

import app.data.models.User;
import app.data.repositories.UserRepository;

public class UserRepositoryMap extends GenericRepositoryMap<User> implements UserRepository {

    @Override
    public void setId(User entity, long id) {
       entity.setId(id);
    }

}