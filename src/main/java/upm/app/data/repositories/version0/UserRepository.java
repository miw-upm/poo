package upm.app.data.repositories.version0;

import upm.app.data.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {

    private final Map<Integer, User> map;
    private int id;

    public UserRepository() {
        this.map = new HashMap<>();
        this.id = 1;
    }

    public void create(User entity) {
        entity.setId(this.id++);
        this.map.put(id, entity);
    }

    public void update(User entity) {
        if (this.map.containsKey(entity.getId())) {
            this.map.put(entity.getId(), entity);
        }
    }

    public User read(Integer id) {
        return this.map.get(id);
    }

    public void deleteById(Integer id) {
        this.map.remove(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(map.values());
    }

}
