package upm.app1.data.repositories;

import upm.app1.data.models.User;

public class Seeder {
    private final UserRepositoryMap userRepository;

    public Seeder(UserRepositoryMap userRepository) {
        this.userRepository = userRepository;
    }

    public void seed() {
        User[] users = {
                new User(666000661, "uno", "Calle 1"),
                new User(666000662, "dos", "Calle 2"),
                new User(666000663, "tres", "Calle 3"),
                new User(666000664, "cuatro", "Calle 4")
        };
        for (int i = 0; i < users.length; i++) {
            users[i] = this.userRepository.create(users[i]);
        }
    }
}