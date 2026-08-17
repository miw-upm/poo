package es.upm.poo.servicies;

import es.upm.poo.data.models.User;
import es.upm.poo.data.repositories.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        this.assertUniqueEmail(user.getEmail());
        return this.userRepository.create(user);
    }

    public User read(Long id) {
        return this.userRepository.read(id)
                .orElseThrow(() -> new IllegalArgumentException("user id not found: " + id));
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    private void assertUniqueEmail(String  email) {
        if (email != null && !this.userRepository.findByEmail(email).isEmpty()) {
            throw new IllegalArgumentException("email already exists: " + email);
        }
    }
}
