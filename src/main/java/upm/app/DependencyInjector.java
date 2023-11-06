package upm.app;

import upm.app.console.CommandLineInterface;
import upm.app.console.ErrorHandler;
import upm.app.console.View;
import upm.app.data.repositories.ArticleRepository;
import upm.app.data.repositories.TagRepository;
import upm.app.data.repositories.UserRepository;
import upm.app.data.repositories.repositories_map.ArticleRepositoryMap;
import upm.app.data.repositories.repositories_map.TagRepositoryMap;
import upm.app.data.repositories.repositories_map.UserRepositoryMap;
import upm.app.services.ArticleService;
import upm.app.services.TagService;
import upm.app.services.UserService;

public class DependencyInjector {
    private final ErrorHandler errorHandler;
    private final View view;
    private final CommandLineInterface commandLineInterface;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final TagService tagService;
    private final ArticleService articleService;

    public DependencyInjector() {
        this.view = new View();

        this.userRepository = new UserRepositoryMap();
        this.tagRepository = new TagRepositoryMap();
        this.articleRepository = new ArticleRepositoryMap();

        this.userService = new UserService(this.userRepository);
        this.tagService = new TagService(this.tagRepository, this.articleRepository);
        this.articleService = new ArticleService(this.articleRepository, this.tagRepository);

        this.commandLineInterface = new CommandLineInterface(this.view, this.userService, this.tagService, this.articleService);

        this.errorHandler = new ErrorHandler(this.commandLineInterface, this.view);
    }

    public void run() {
        this.errorHandler.handlesErrors();
    }

    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    public View getView() {
        return this.view;
    }

    public CommandLineInterface getCommandLineInterface() {
        return this.commandLineInterface;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public TagRepository getTagRepository() {
        return this.tagRepository;
    }

    public ArticleRepository getArticleRepository() {
        return this.articleRepository;
    }

    public UserService getUserService() {
        return this.userService;
    }

    public TagService getTagService() {
        return this.tagService;
    }

    public ArticleService getArticleService() {
        return this.articleService;
    }
}
