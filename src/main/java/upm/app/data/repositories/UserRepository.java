package upm.app.data.repositories;


import upm.app.data.models.User;

public class UserRepository extends GenericRepository<User> {

    @Override
    public Integer getId(User entity) {
        return entity.getId();
    }

    @Override
    public void setId(User entity, Integer id) {
        entity.setId(id);
    }

}
