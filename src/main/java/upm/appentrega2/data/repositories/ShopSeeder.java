package upm.appentrega2.data.repositories;

import upm.appentrega2.data.models.Article;
import upm.appentrega2.data.models.User;

import java.time.LocalDate;

public class ShopSeeder {
    private final UserRepositoryMap userRepository;
    private final ArticleRepositoryMap articleRepository;

    public ShopSeeder(UserRepositoryMap userRepository, ArticleRepositoryMap articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
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
                new Article("8412345123450", "art1", 10.0, "prov1"),
                new Article("8412345123460", "art2", 1.0, "prov1"),
                new Article("8412345123470", "art3", 2.0, "prov2"),
                new Article("8412345123480", "art4", 10.2, "prov2"),
                new Article("8412345123490", "art5", 10.0, "prov3"),
        };
        for (int i = 0; i < articles.length; i++) {
            articles[i].setRegistrationDate(LocalDate.now());
            articles[i] = this.articleRepository.create(articles[i]);
        }
    }

}
