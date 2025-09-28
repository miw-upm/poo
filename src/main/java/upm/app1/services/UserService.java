package upm.app1.services;

import upm.app1.data.models.User;
import upm.app1.data.repositories.UserRepositoryMap;

import java.util.List;

public class UserService {
    private final UserRepositoryMap userRepository;

    public UserService(UserRepositoryMap userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        if (this.userRepository.findByMobile(user.getMobile()).isPresent()) {
            throw new RuntimeException("El móvil ya existe, y debiera ser único: " + user.getMobile());
        }
        return this.userRepository.create(user);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
