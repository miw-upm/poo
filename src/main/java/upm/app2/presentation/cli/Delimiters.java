package upm.app2.presentation.cli;

public enum Delimiters {
    COMMAND_PARAM_SEPARATOR("#"), PARAM_SEPARATOR(",");

    private final String value;

    Delimiters(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
