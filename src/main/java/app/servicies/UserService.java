package app.servicies;

import app.data.models.User;
import app.data.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        this.assertUniqueEmail(user.getEmail());
        return this.userRepository.create(user);
    }

    private void assertUniqueEmail(String  email) {
        if (email != null && !this.userRepository.findByEmail(email).isEmpty()) {
            throw new IllegalArgumentException("email already exists: " + email);
        }
    }
}
