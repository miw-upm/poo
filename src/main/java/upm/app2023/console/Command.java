package upm.app2023.console;

public interface Command {
    void execute(String[] values);

    String name();

    String help();
}
