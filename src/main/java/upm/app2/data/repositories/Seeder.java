package upm.app2.data.repositories;

import upm.app2.data.models.Article;
import upm.app2.data.models.ArticleItem;
import upm.app2.data.models.ShoppingCart;
import upm.app2.data.models.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Seeder {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public Seeder(UserRepository userRepository, ArticleRepository articleRepository, ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void seed() {
        User[] users = {
                new User(666000661, "uno", "Calle 1"),
                new User(666000662, "dos", "Calle 2"),
                new User(666000663, "tres", "Calle 3"),
                new User(666000664, "cuatro", "Calle 4")
        };
        for (int i = 0; i < users.length; i++) {
            users[i] = this.userRepository.create(users[i]);
        }
        Article[] articles = {
                Article.builder().barcode("8412345123410").summary("art1").price(BigDecimal.TEN).provider("prov1").build(),
                Article.builder().barcode("8412345123420").summary("art2").price(BigDecimal.ONE).provider("prov1").build(),
                Article.builder().barcode("8412345123430").price(BigDecimal.TEN).build(),
                Article.builder().barcode("8412345123440").summary("art4").price(BigDecimal.TWO).provider("prov2").build(),
                Article.builder().barcode("8412345123450").summary("art5").price(new BigDecimal("10.2")).provider("prov2").build(),
                Article.builder().barcode("8412345123460").summary("art5").price(BigDecimal.TEN).provider("prov3").build(),
        };
        for (int i = 0; i < articles.length; i++) {
            articles[i].setRegistrationDate(LocalDate.now());
            articles[i] = this.articleRepository.create(articles[i]);
        }

        ArticleItem[] articleItems = {
                new ArticleItem(articles[0], 1, new BigDecimal("11.346")),
                new ArticleItem(articles[1], 2, BigDecimal.TEN),
                new ArticleItem(articles[1], 1, BigDecimal.ONE),
                new ArticleItem(articles[2], 20, BigDecimal.ZERO)
        };
        ShoppingCart[] carts = {
                new ShoppingCart(users[0], LocalDateTime.now()),
                new ShoppingCart(users[1], LocalDateTime.now())
        };
        carts[0].add(articleItems[0]);
        carts[0].add(articleItems[1]);
        carts[1].add(articleItems[2]);
        carts[1].add(articleItems[3]);
        for (ShoppingCart cart : carts) {
            this.shoppingCartRepository.create(cart);
        }
    }
}