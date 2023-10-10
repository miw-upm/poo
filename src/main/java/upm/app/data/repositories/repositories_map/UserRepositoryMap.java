package upm.app.data.repositories.repositories_map;


import upm.app.data.models.User;
import upm.app.data.repositories.UserRepository;

public class UserRepositoryMap extends GenericRepositoryMap<User> implements UserRepository {

    @Override
    protected Integer getId(User entity) {
        return entity.getId();
    }

    @Override
    protected void setId(User entity, Integer id) {
        entity.setId(id);
    }

}
