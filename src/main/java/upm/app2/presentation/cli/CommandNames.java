package upm.app2.presentation.cli;

public enum CommandNames {
    CREATE_USER("create-user", "<mobile>,<name>,<address>", "Se crea un usuario"),
    LIST_USERS("list-users", "", "Muestra todos los usuarios"),
    HELP("help", "", "Muestra la ayuda"),
    EXIT("exit", "", "Termina la ejecución");

    private final String value;
    private final String parameters;
    private final String help;

    CommandNames(String value, String parametters, String help) {
        this.value = value;
        this.parameters = parametters;
        this.help = help;
    }

    public static CommandNames fromValue(String value) {
        for (CommandNames command : CommandNames.values()) {
            if (command.getValue().equals(value)) {
                return command;
            }
        }
        throw new UnsupportedOperationException("Comando '" + value + "' no existe.");
    }

    public String getValue() {
        return this.value;
    }

    public String getParameters() {
        return parameters;
    }

    public String getHelp() {
        String params = this.getParameters().isEmpty() ? "Sin parámetros" : this.getParameters();
        return this.getValue() + " (" + params + ")   " + this.help;
    }
}
