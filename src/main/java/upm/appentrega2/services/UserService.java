package upm.appentrega2.services;

import upm.appentrega2.data.models.User;
import upm.appentrega2.data.repositories.UserRepository;
import upm.appentrega2.services.exceptions.DuplicateException;
import upm.appentrega2.services.exceptions.UnauthorizedException;

import java.util.List;
import java.util.Optional;

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

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User login(Integer mobile, String password) {
        Optional<User> user = this.userRepository.findByMobile(mobile);
        if (user.isEmpty()) {
            throw new UnauthorizedException("Movil o contraseña incorrecto");
        }
        if (!password.equals(user.get().getPassword())) {
            throw new UnauthorizedException("Movil o contraseña incorrecto");
        }
        return user.get();
    }
}
