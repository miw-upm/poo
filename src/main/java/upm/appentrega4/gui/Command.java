package upm.appentrega4.gui;

import upm.appentrega4.data.models.Rol;

import java.util.List;

public interface Command {
    String name();

    List<String> params();

    List<Rol> allowedRoles();

    String helpMessage();

    void execute();
}
