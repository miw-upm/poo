package upm.app1.data.repositories;

import upm.app1.data.models.User;

import java.util.*;

public class UserRepositoryMap {

    private final Map<Integer, User> map;
    private int id;

    public UserRepositoryMap() {
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

    public Optional<User> findByMobile(Integer mobile) {
        for (User user : this.findAll()) {
            if (mobile.equals(user.getMobile())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public List<User> findAll() {
        return new ArrayList<>(map.values());
    }

}
