package upm.appentrega3.console;

import upm.appentrega3.data.models.Rol;

import java.util.List;

public interface Command {
    String name();

    List<String> params();

    List<Rol> allowedRoles();

    String helpMessage();

    void execute(String[] values);

    default String help() {
        return this.name() + Delimiters.COMMAND.getValue() + String.join(Delimiters.PARAM.getValue(), this.params()) + ". " + this.helpMessage() + ".";
    }
}
