package upm.appentrega4.gui;

import upm.appentrega4.data.models.Rol;

import java.util.List;

public interface Command {

    String name();

    List<String> params();

    List<Rol> allowedRoles();

    String helpMessage();

    List<Object> execute(String[] params);

    default String help() {
        StringBuilder result = new StringBuilder(this.name());
        if (!this.params().isEmpty()) {
            result.append(Delimiters.COMMAND.getValue()).append(String.join(Delimiters.PARAM.getValue(), this.params()));
        }
        return result.append(". ").append(this.helpMessage()).append(".").toString();
    }
}
