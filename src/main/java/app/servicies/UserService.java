package app.servicies;

import app.data.models.User;
import app.data.repositories.UserRepository;

import java.util.Objects;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        if (user.getEmail() != null && !this.userRepository.findByEmail(user.getEmail()).isEmpty()) {
            throw new IllegalArgumentException("El email ya existe, y debiera ser único: " + user.getEmail());
        }
        return this.userRepository.create(user);
    }
}
