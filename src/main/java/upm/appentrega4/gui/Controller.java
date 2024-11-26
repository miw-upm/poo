package upm.appentrega4.gui;

import javafx.scene.layout.VBox;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.User;
import upm.appentrega4.gui.fx.components.Status;

import java.util.*;

public class Controller {
    private final Map<String, Command> commands;
    private User user;
    private VBox contentArea;
    private Status status;

    public Controller() {
        this.commands = new HashMap<>();
    }

    public void setContentArea(VBox contentArea) {
        this.contentArea = contentArea;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void add(Command command) {
        this.commands.put(command.name(), command);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String userName() {
        if (Objects.isNull(this.user)) {
            return "";
        } else {
            return this.user.getName();
        }
    }

    public List<String> keys() {
        return this.commands.keySet().stream()
                .filter(aKey -> this.command(aKey).allowedRoles().contains(this.userRol()))
                .toList();
    }

    public Command command(String key) {
        return this.commands.get(key);
    }

    private Rol userRol() {
        if (Objects.isNull(this.user)) {
            return Rol.NONE;
        } else {
            return this.user.getRol();
        }
    }

    public List<String> help() {
        List<String> list = new ArrayList<>();
        for (Command command : this.commands.values()) {
            if (command.allowedRoles().contains(this.userRol())) {
                list.add(command.help());
            }
        }
        return list;
    }
}
