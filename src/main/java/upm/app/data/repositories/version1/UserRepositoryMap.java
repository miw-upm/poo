package upm.app.data.repositories.version1;


import upm.app.data.models.User;

public class UserRepositoryMap extends GenericRepositoryMap<User> {

    @Override
    protected Integer getId(User entity) {
        return entity.getId();
    }

    @Override
    protected void setId(User entity, Integer id) {
        entity.setId(id);
    }

}
