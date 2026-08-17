package es.upm.poo.servicies;

import es.upm.poo.data.models.User;
import es.upm.poo.data.repositories.UserRepository;
import es.upm.poo.servicies.exceptions.ConflictException;
import es.upm.poo.servicies.exceptions.NotFoundException;

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
                .orElseThrow(() -> new NotFoundException("user id not found: " + id));
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User update(Long id, User user) {
        User userDb = this.read(id);
        if (user.getEmail() != null && !user.getEmail().equals(userDb.getEmail())) {
            this.assertUniqueEmail(user.getEmail());
        }
        return this.userRepository.update(id, user);
    }

    private void assertUniqueEmail(String email) {
        if (email != null && !this.userRepository.findByEmail(email).isEmpty()) {
            throw new ConflictException("email already exists: " + email);
        }
    }
}
