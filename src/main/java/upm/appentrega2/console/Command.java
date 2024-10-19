package upm.appentrega2.console;

public enum Command {
    CREATE_USER("create-user", ". Se crea un usuario.", "mobile", "password", "name", "address"),
    LIST_USERS("list-users", ". Lista todos los usuarios"),
    CREATE_ARTICLE("create-article", ". Se crea un artículo", "barcode", "summary", "price", "provider"),
    LIST_ARTICLES("list-articles", ". Lista los artículos"),

    HELP("help", ". Muestra la ayuda."),
    EXIT("exit", ". Termina la ejecución.");

    private final String value;
    private final String[] params;
    private final String help;

    Command(String value, String help, String... params) {
        this.value = value;
        this.help = help;
        this.params = params;
    }

    public static Command fromValue(String value) {
        for (Command command : Command.values()) {
            if (value.equals(command.getValue())) {
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

    public String getHelp() {
        String finalHelp = this.getValue();
        if (this.params.length != 0) {
            finalHelp += Delimiters.COMMAND.getValue() + String.join(Delimiters.PARAM.getValue(), this.params);
        }
        return finalHelp + this.help;
    }
}
