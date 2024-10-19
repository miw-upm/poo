package upm.app2023.console.version2;

public enum CommandNames {
    CREATE_USER("create-user", ":<mobile>;<name>;<address> Se crea un usuario"),
    CREATE_ARTICLE("create-article", ":<barcode>;<summary>;<price>;<provider>? Se crea un articulo, proveedor es opcional"),
    FIND_ARTICLE_BY_TAG_NAME("find-article-by-tag-name", ":<tag> Presenta los artículos asociados a una etiqueta"),
    FIND_TAG_BY_ARTICLE_BARCODE("find-tag-by-article-barcode", ":<codigo-barras> Presenta las atiquetas asociadas al artículo"),
    HELP("help", ": muestra la ayuda."),
    EXIT("exit", ": termina la ejecución.");

    private final String value;
    private final String help;

    CommandNames(String value, String help) {
        this.value = value;
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

    public String getHelp() {
        return this.getValue() + this.help;
    }
}
