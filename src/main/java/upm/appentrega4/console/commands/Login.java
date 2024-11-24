package upm.appentrega4.console.commands;

import upm.appentrega4.console.Command;
import upm.appentrega4.console.CommandLineInterface;
import upm.appentrega4.data.models.Rol;
import upm.appentrega4.data.models.User;
import upm.appentrega4.services.UserService;

import java.util.List;

public class Login implements Command {
    private final UserService userService;
    private final CommandLineInterface commandLineInterface;

    public Login(CommandLineInterface commandLineInterface, UserService userService) {
        this.commandLineInterface = commandLineInterface;
        this.userService = userService;
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public List<String> params() {
        return List.of("mobile", "password");
    }

    @Override
    public List<Rol> allowedRoles() {
        return Rol.all();
    }

    @Override
    public String helpMessage() {
        return "Inicia sesión";
    }

    @Override
    public void execute(String[] params) {
        User userLogged = this.userService.login(Integer.valueOf(params[0]), params[1]);
        this.commandLineInterface.setUser(userLogged);
    }
}
