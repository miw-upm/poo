package upm.app.console;

public interface Command {
    void execute(String[] values);

    String name();

    String help();
}
