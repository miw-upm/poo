package upm.app2023.console.version1;


import upm.app2023.data.repositories.ArticleRepository;
import upm.app2023.data.repositories.TagRepository;
import upm.app2023.data.repositories.UserRepository;
import upm.app2023.data.repositories.repositories_map.ArticleRepositoryMap;
import upm.app2023.data.repositories.repositories_map.TagRepositoryMap;
import upm.app2023.data.repositories.repositories_map.UserRepositoryMap;
import upm.app2023.services.ArticleService;
import upm.app2023.services.TagService;
import upm.app2023.services.UserService;

public class App {

    public static void main(String[] args) {
        System.out.println("Run App..version1");
        UserRepository userRepository = new UserRepositoryMap();
        TagRepository tagRepository = new TagRepositoryMap();
        ArticleRepository articleRepository = new ArticleRepositoryMap();

        UserService userService = new UserService(userRepository);
        TagService tagService = new TagService(tagRepository, articleRepository);
        ArticleService articleService = new ArticleService(articleRepository, tagRepository);

        CommandLineInterface commandLineInterface = new CommandLineInterface(userService, tagService, articleService);

        boolean exit = false;
        while (!exit) {
            try {
                exit = commandLineInterface.runCommands();
            } catch (Exception e) {
                System.out.println(">>> ERROR (" + e.getClass().getSimpleName() + ") >>> " + e.getMessage());
            }
        }
        System.out.println("Hasta pronto!");
    }

}

