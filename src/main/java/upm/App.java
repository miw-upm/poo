package upm;


import upm.app.console.version1.CommandLineInterface;
import upm.app.data.repositories.ArticleRepository;
import upm.app.data.repositories.TagRepository;
import upm.app.data.repositories.UserRepository;
import upm.app.data.repositories.repositories_map.ArticleRepositoryMap;
import upm.app.data.repositories.repositories_map.TagRepositoryMap;
import upm.app.data.repositories.repositories_map.UserRepositoryMap;
import upm.app.services.ArticleService;
import upm.app.services.TagService;
import upm.app.services.UserService;

public class App {

    public static void main(String[] args) {
        System.out.println("Run App..");
        UserRepository userRepository = new UserRepositoryMap();
        TagRepository tagRepository = new TagRepositoryMap();
        ArticleRepository articleRepository = new ArticleRepositoryMap();

        UserService userService = new UserService(userRepository);
        TagService tagService = new TagService(tagRepository,articleRepository);
        ArticleService articleService = new ArticleService(tagRepository);

        new CommandLineInterface(userService,tagService,articleService).runCommand();
    }
}
