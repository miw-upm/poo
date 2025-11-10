package upm.app2.services;

import upm.app2.data.models.*;
import upm.app2.data.repositories.ArticleRepository;
import upm.app2.data.repositories.ShoppingCartRepository;
import upm.app2.data.repositories.UserRepository;
import upm.app2.services.exceptions.NotFoundException;

import java.time.LocalDateTime;

public class ShoppingCartService {
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ArticleRepository articleRepository;

    public ShoppingCartService(UserRepository userRepository, ArticleRepository articleRepository, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.articleRepository = articleRepository;
    }

    public ShoppingCart create(Integer userId, ArticleItemCreationDto dto) {
        User user = this.userRepository.read(userId)
                .orElseThrow(() -> new NotFoundException("No se encuentra la id de usuario: " + userId));
        Article article = this.articleRepository.read(dto.articleId())
                .orElseThrow(() -> new NotFoundException("No se encuentra el barcode de article: " + dto.articleId()));
        ShoppingCart cart = new ShoppingCart(user, LocalDateTime.now());
        cart.add(new ArticleItem(article, dto.amount(), dto.discount()));
        return shoppingCartRepository.create(cart);
    }

    public ShoppingCart add(Integer id, ArticleItemCreationDto dto) {
        ShoppingCart cart = this.shoppingCartRepository.read(id)
                .orElseThrow(() -> new NotFoundException("No se encuentra la id de carrito: " + id));
        Article article = this.articleRepository.read(dto.articleId())
                .orElseThrow(() -> new NotFoundException("No se encuentra el barcode de article: " + dto.articleId()));
        cart.add(new ArticleItem(article, dto.amount(), dto.discount()));
        return this.shoppingCartRepository.update(cart.getId(), cart);
    }
}
