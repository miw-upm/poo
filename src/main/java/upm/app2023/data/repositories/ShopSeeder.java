package upm.app2023.data.repositories;

import upm.app2023.data.models.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ShopSeeder {
    private final ArticleRepository articleRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    public ShopSeeder(ArticleRepository articleRepository, ShoppingCartRepository shoppingCartRepository, TagRepository tagRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    public void seed() {
        User[] users = {
                new User(666000660, "user1", "Calle 1"),
                new User(666000661, "user2", "Calle 2"),
                new User(666000662, "user3", "Calle 3"),
                new User(666000663, "user4", "Calle 4")
        };
        for (int i = 0; i < users.length; i++) {
            users[i] = this.userRepository.create(users[i]);
        }

        Article[] articles = {
                new Article("8412345123450", "art1", BigDecimal.TEN, "prov1"),
                new Article("8412345123460", "art2", BigDecimal.ONE, "prov1"),
                new Article("8412345123470", "art3", BigDecimal.TWO, "prov2"),
                new Article("8412345123480", "art4", BigDecimal.valueOf(10.2), "prov2"),
                new Article("8412345123490", "art5", BigDecimal.TEN, "prov3"),
        };
        for (int i = 0; i < articles.length; i++) {
            articles[i].setRegistrationDate(LocalDate.now());
            articles[i] = this.articleRepository.create(articles[i]);
        }

        Tag[] tags = {
                new Tag("tag1", "tag 1"),
                new Tag("tag2", "tag 2"),
                new Tag("tag3", "tag 3"),
                new Tag("tag4", "tag 4"),
                new Tag("tag5", "tag 5")
        };
        tags[0].addArticle(articles[0]);
        tags[0].addArticle(articles[1]);
        tags[1].addArticle(articles[0]);
        tags[1].addArticle(articles[2]);
        tags[3].addArticle(articles[3]);
        tags[3].addArticle(articles[4]);
        for (Tag tag : tags) {
            this.tagRepository.create(tag);
        }


        ArticleItem[] articleItems = {
                new ArticleItem(articles[0], 1, new BigDecimal("11.346")),
                new ArticleItem(articles[1], 2, BigDecimal.TEN),
                new ArticleItem(articles[1], 3, BigDecimal.ZERO),
                new ArticleItem(articles[2], 4, BigDecimal.ONE)
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
