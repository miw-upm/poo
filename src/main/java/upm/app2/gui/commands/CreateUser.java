package upm.app2.gui.commands;

import upm.app2.data.models.User;
import upm.app2.gui.Command;
import upm.app2.gui.fx.GraphicalUserInterfaceFX;
import upm.app2.gui.fx.components.entries.GenericContentArea;
import upm.app2.services.UserService;

import java.util.List;
import java.util.function.Consumer;

public class CreateUser implements Command {
    private final GraphicalUserInterfaceFX view;
    private final UserService userService;


    public CreateUser(GraphicalUserInterfaceFX view, UserService userService) {
        this.view = view;
        this.userService = userService;
    }

    @Override
    public String name() {
        return "create-user";
    }

    @Override
    public void prepareAndExecute() {
        Consumer<List<String>> consumer = params -> {
            User user = this.userService.create(new User(Integer.valueOf(params.getFirst()), params.get(1), params.get(2)));
            this.view.getStatus().successful(user.toString());
        };
        GenericContentArea content = new GenericContentArea(consumer, this.view.getStatus());
        content.addTextFields("mobile");
        content.addTextFields("name");
        content.addTextFields("address");
        this.view.setContentArea(content);
    }

}
