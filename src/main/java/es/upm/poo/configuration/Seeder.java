package es.upm.poo.configuration;

import es.upm.poo.data.model.User;
import es.upm.poo.services.UserService;

public class Seeder {
    private final UserService userService;

    public Seeder(UserService userService) {
        this.userService = userService;
    }

    public void seed() {
        this.userService.create(new User("Alice Johnson", "alice.johnson@upm.es", 19, true));
        this.userService.create(new User("Marco Rossi", null, 42, false));
        this.userService.create(new User("Nadia Chen", "nadia.chen@upm.es", null, true));
        this.userService.create(new User("Omar Haddad", null, 61, null));
        this.userService.create(new User("Sofia Garcia", "sofia.garcia@upm.es", 34, false));
    }
}
