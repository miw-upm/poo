package upm.appentrega3.console;

import upm.appentrega2.console.Delimiters;
import upm.appentrega2.data.models.Rol;

import java.util.List;

public enum BasicCommand {
    HELP("help", "Muestra la ayuda", Rol.all()),
    EXIT("exit", "Termina la ejecución", Rol.authenticated()),
    LOGIN("login", "Inicia sesión", Rol.all(), "mobile", "password"),
    LOGOUT("logout", "Cierra sesión", Rol.authenticated());

    private final String name;
    private final String help;
    private final List<Rol> allowedRoles;
    private final String[] params;


    BasicCommand(String name, String help, List<Rol> allowedRoles, String... params) {
        this.name = name;
        this.help = help;
        this.allowedRoles = allowedRoles;
        this.params = params;
    }

    public String getName() {
        return this.name;
    }

    public int paramsNumber() {
        return this.params.length;
    }

    public List<Rol> getAllowedRoles() {
        return this.allowedRoles;
    }

    public String getHelp() {
        return this.name + Delimiters.COMMAND.getValue() + String.join(Delimiters.PARAM.getValue(), this.params) + ". " + this.help + ".";
    }
}
