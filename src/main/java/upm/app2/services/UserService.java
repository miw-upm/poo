package upm.app2.services;

import upm.app2.data.models.ShoppingCart;
import upm.app2.data.models.User;
import upm.app2.data.repositories.ShoppingCartRepository;
import upm.app2.data.repositories.UserRepository;
import upm.app2.services.exceptions.DuplicateException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public UserService(UserRepository userRepository, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
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

    public List<Integer> findUserMobilesByCartGreater100() {
        List<Integer> mobiles = new ArrayList<>();
        for(ShoppingCart shoppingCart: this.shoppingCartRepository.findAll()){
            System.out.println(">>>: " + shoppingCart);
            if (shoppingCart.total().compareTo(new BigDecimal("100")) > 0){
                mobiles.add(shoppingCart.getUser().getMobile());
            }
        }
        return mobiles;
    }
}
