package upm.app.data.repositories.version0;

import upm.app.data.models.User;

import java.util.*;

public class UserRepository {

    private final Map<Integer, User> map;
    private int id;

    public UserRepository() {
        this.map = new HashMap<>();
        this.id = 1;
    }

    public User create(User entity) {
        entity.setId(this.id);
        this.map.put(id, entity);
        this.id++;
        return entity;
    }

    public User update(User entity) {
        if (this.map.containsKey(entity.getId())) {
            this.map.put(entity.getId(), entity);
        }
        return entity;
    }

    public Optional<User> read(Integer id) {
        return Optional.ofNullable(this.map.get(id));
    }

    public void deleteById(Integer id) {
        this.map.remove(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(map.values());
    }

}
