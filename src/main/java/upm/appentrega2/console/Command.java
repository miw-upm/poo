package upm.appentrega2.console;

import upm.appentrega2.data.models.Rol;

import java.util.List;

public enum Command {
    HELP("help", ". Muestra la ayuda.", Rol.all()),
    EXIT("exit", ". Termina la ejecución.", Rol.authenticated()),
    LOGIN("login", ". Inicia sesión", Rol.all(), "mobile", "password"),
    LOGOUT("logout", ". Cierra sesión", Rol.authenticated()),
    CREATE_USER("create-user", ". Se crea un usuario.", Rol.all(), "mobile", "password", "name", "address"),
    LIST_USERS("list-users", ". Lista todos los usuarios", List.of(Rol.ADMIN, Rol.MANAGEMENT)),
    CREATE_ARTICLE("create-article", ". Se crea un artículo", List.of(Rol.ADMIN, Rol.MANAGEMENT), "barcode", "summary", "price", "provider"),
    LIST_ARTICLES("list-articles", ". Lista los artículos", Rol.authenticated());

    private final String value;
    private final String help;
    private final List<Rol> allowedRoles;

    private final String[] params;


    Command(String value, String help, List<Rol> allowedRoles, String... params) {
        this.value = value;
        this.help = help;
        this.allowedRoles = allowedRoles;
        this.params = params;
    }

    public static Command fromValue(String value, Rol userRoll) {
        for (Command command : Command.values()) {
            if (value.equals(command.getValue())) {
                if (!command.allowedRoles.contains(userRoll)) {
                    throw new RuntimeException("Prohibido, no tiene permiso suficiente");
                }
                return command;
            }
        }
        throw new UnsupportedOperationException("Comando '" + value + "' no existe.");
    }

    public String getValue() {
        return this.value;
    }

    public String[] getParams() {
        return this.params;
    }

    public String getHelp(Rol userRoll) {
        String finalHelp;
        if (this.allowedRoles.contains(userRoll)) {
            finalHelp = this.getValue();
            if (this.params.length != 0) {
                finalHelp += Delimiters.COMMAND.getValue() + String.join(Delimiters.PARAM.getValue(), this.params);
            }
            return finalHelp + this.help;
        } else {
            return "";
        }
    }
}
