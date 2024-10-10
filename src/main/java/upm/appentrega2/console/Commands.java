package upm.appentrega2.console;

public enum Commands {
    CREATE_USER("create-user", ". Se crea un usuario.", "<mobile>", "<name>", "<address>"),
    LIST_USERS("list-users", ". Lista todos los usuarios"),

    HELP("help", ". Muestra la ayuda."),
    EXIT("exit", ". Termina la ejecución.");

    private final String value;
    private final String[] params;
    private final String help;

    Commands(String value, String help, String... params) {
        this.value = value;
        this.help = help;
        this.params = params;
    }

    public static Commands fromValue(String value) {
        for (Commands command : Commands.values()) {
            if (value.equals(command.getValue())) {
                return command;
            }
        }
        throw new UnsupportedOperationException("Comando '" + value + "' no existe.");
    }

    public String getValue() {
        return this.value;
    }

    public int length() {
        return this.params.length;
    }

    public String getHelp() {
        String finalHelp = this.getValue();
        if (this.params.length != 0) {
            finalHelp += Delimiters.COMMAND.getValue() + String.join(Delimiters.PARAM.getValue(), this.params);
        }
        return finalHelp + this.help;
    }
}
