package upm.introduction.paradigm;

import java.time.LocalDateTime;

public class Imperative {
    public static void main(String[] args) {
        System.out.println("POO");

        final int MIDDAY = 12;
        final String NAME = "Jesús";
        if (LocalDateTime.now().getHour() < MIDDAY) {
            System.out.println("Buenos días " + NAME + "!");
        } else {
            System.out.println("Buenas tardes " + NAME + "!");
        }

        final int[] array = {0, 1, 2, 3};
        int accumulator = 0;
        for (int i = 0; i < array.length; i++) {
            accumulator += array[i];
        }
        System.out.println("Suma (for i): " + accumulator);
        int max = -1; // solo para listas con valores naturales
        for (int item : array) {
            if (item > max) {
                max = item;
            }
        }
        System.out.println("Máximo (for each): " + max);
    }
}
