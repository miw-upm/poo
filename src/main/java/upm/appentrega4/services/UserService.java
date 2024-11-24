package upm.appentrega4.services;

import upm.appentrega4.data.models.User;
import upm.appentrega4.data.repositories.UserRepository;
import upm.appentrega4.services.exceptions.DuplicateException;
import upm.appentrega4.services.exceptions.UnauthorizedException;

import java.util.stream.Stream;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        this.userRepository.findByMobile(user.getMobile()).ifPresent(existingUser -> {
            throw new DuplicateException("El móvil ya existe, y debiera ser único: " + user.getMobile());
        });
        return this.userRepository.create(user);
    }

    public Stream<User> findAll() {
        return this.userRepository.findAll().stream();
    }

    public User login(Integer mobile, String password) {
        return this.userRepository.findByMobile(mobile)
                .filter(user -> password.equals(user.getPassword()))
                .orElseThrow(() -> new UnauthorizedException("Móvil o contraseña incorrecto"));
    }
}