package upm.app2.gui;

public interface Command {
    String name();

    void prepareAndExecute();
}
