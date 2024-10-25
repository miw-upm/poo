package upm.appentrega3.services;

import upm.appentrega3.data.models.User;
import upm.appentrega3.data.repositories.UserRepository;
import upm.appentrega3.services.exceptions.DuplicateException;
import upm.appentrega3.services.exceptions.UnauthorizedException;

import java.util.List;
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