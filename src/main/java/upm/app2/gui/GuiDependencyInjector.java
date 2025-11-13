package upm.app2.gui;

import upm.app2.data.repositories.ArticleRepository;
import upm.app2.data.repositories.Seeder;
import upm.app2.data.repositories.ShoppingCartRepository;
import upm.app2.data.repositories.UserRepository;
import upm.app2.data.repositories.mysql.ArticleRepositorySql;
import upm.app2.data.repositories.mysql.RepositoryMysql;
import upm.app2.data.repositories.mysql.ShoppingCartRepositorySql;
import upm.app2.data.repositories.mysql.UserRepositorySql;
import upm.app2.gui.commands.*;
import upm.app2.gui.fx.GraphicalUserInterfaceFX;
import upm.app2.services.ArticleService;
import upm.app2.services.ShoppingCartService;
import upm.app2.services.UserService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiDependencyInjector {
    private static GuiDependencyInjector instance;

    private final GraphicalUserInterfaceFX view;
    private final Map<String, List<Command>> commandsByGroup = new HashMap<>();
    private final UserService userService;
    private final ArticleService articleService;
    private final ShoppingCartService shoppingCartService;
    private final ArticleRepository articleRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;

    private GuiDependencyInjector(GraphicalUserInterfaceFX view) {
        this.view = view;
        Connection connection = new RepositoryMysql().createConnection();
        this.userRepository = new UserRepositorySql(connection);
        this.articleRepository = new ArticleRepositorySql(connection);
        this.shoppingCartRepository = new ShoppingCartRepositorySql(connection, (UserRepositorySql) this.userRepository, (ArticleRepositorySql) this.articleRepository);

        new Seeder(this.userRepository, this.articleRepository, this.shoppingCartRepository).seed();

        this.userService = new UserService(this.userRepository, this.shoppingCartRepository);
        this.articleService = new ArticleService(this.articleRepository);
        this.shoppingCartService = new ShoppingCartService(userRepository, articleRepository, shoppingCartRepository);

        this.commandsByGroup.put("users", List.of(
                new CreateUser(this.view, this.userService),
                new ListUsers(this.userService),
                new FindUserMobilesByCartGreater100(this.userService)
        ));
        this.commandsByGroup.put("carts", List.of(
                new CreateShoppingCart(this.view, this.userService, this.articleService, this.shoppingCartService),
                new AddShoppingCart(this.view, this.articleService, this.shoppingCartService),
                new ListShoppingCarts(this.shoppingCartService)
        ));
    }

    public static void createInstance(GraphicalUserInterfaceFX view) {
        if (instance == null) {
            instance = new GuiDependencyInjector(view);
        }
    }

    public static GuiDependencyInjector getInstance() {
        return GuiDependencyInjector.instance;
    }

    public GraphicalUserInterfaceFX getView() {
        return view;
    }

    public UserService getUserService() {
        return userService;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public ArticleRepository getArticleRepository() {
        return articleRepository;
    }

    public ShoppingCartRepository getShoppingCartRepository() {
        return shoppingCartRepository;
    }

    public ShoppingCartService getShoppingCartService() {
        return shoppingCartService;
    }

    public Map<String, List<Command>> getCommandsByGroup() {
        return commandsByGroup;
    }
}
