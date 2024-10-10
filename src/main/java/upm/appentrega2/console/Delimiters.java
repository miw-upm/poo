package upm.appentrega2.console;

public enum Delimiters {
    COMMAND(":"), PARAM(",");

    private final String value;

    Delimiters(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
