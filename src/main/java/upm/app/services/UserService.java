package upm.app.services;

import upm.app.data.models.User;
import upm.app.data.repositories.UserRepository;
import upm.app.services.exceptions.DuplicateException;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        if (this.userRepository.findByMobile(user.getMobile()).isPresent()) {
            throw new DuplicateException("El móvil ya existe, y debiera ser único: " + user.getMobile());
        }
        return this.userRepository.create(user);
    }

}
