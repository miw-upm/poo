package upm.app2.gui.commands;

import javafx.scene.layout.BorderPane;
import upm.app2.data.models.User;
import upm.app2.gui.Command;
import upm.app2.gui.fx.components.Status;
import upm.app2.gui.fx.components.entries.GenericContentArea;
import upm.app2.services.UserService;

import java.util.List;
import java.util.function.Consumer;

public class CreateUser implements Command {
    private final BorderPane contentArea;
    private final Status status;
    private final UserService userService;


    public CreateUser(BorderPane contentArea, Status status, UserService userService) {
        this.contentArea = contentArea;
        this.status = status;
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
            this.status.successful(user.toString());
        };
        GenericContentArea content = new GenericContentArea(consumer, this.status);
        content.addTextFields("mobile");
        content.addTextFields("name");
        content.addTextFields("address");
        this.contentArea.setCenter(content);
    }

}
