package upm.appentrega2.data.models;

import java.util.Arrays;
import java.util.List;

public enum Rol {
    ADMIN, MANAGEMENT, CUSTOMER, NONE;

    public static List<Rol> all() {
        return Arrays.asList(Rol.values());
    }

    public static List<Rol> authenticated() {
        return all().stream()
                .filter(rol -> rol != Rol.NONE)
                .toList();
    }
}