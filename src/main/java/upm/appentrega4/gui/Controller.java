package upm.appentrega4.gui;

import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Controller {
    private final Map<String, Command> commands;
    private User user;

    public Controller() {
        this.commands = new HashMap<>();
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
                .filter(aKey -> !aKey.equals("login"))
                .filter(aKey -> !aKey.equals("logout"))
                .toList();
    }

    public Command command(String key) {
        return this.commands.get(key);
    }

    public Rol userRol() {
        if (Objects.isNull(this.user)) {
            return Rol.NONE;
        } else {
            return this.user.getRol();
        }
    }

}
