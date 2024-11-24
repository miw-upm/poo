package upm.appentrega4.console;

public enum Delimiters {
    COMMAND(":"), PARAM(",");

    private final String value;

    Delimiters(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
