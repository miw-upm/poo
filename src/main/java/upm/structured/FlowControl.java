package upm.structured;

import java.util.Arrays;

public class FlowControl {

    public static void main(String[] args) {

        final int ADULT = 18;
        int age = 20;
        if (age >= ADULT) {
            System.out.println("Mayor de edad");
        } else {
            System.out.println("Menor de edad");
        }

        if (age >= 0 && age < ADULT) {
            System.out.println("Menor de edad");
        } else if (age >= ADULT) {
            System.out.println("Mayor de edad");
        } else {
            System.out.println("ERROR, valor no previsto");
        }

        String month = "February";
        switch (month) {
            case "January":
            case "February":
            case "March":
                System.out.println("It's Winter");
                break;
            default:
                System.out.println("Error");
        }

        int value = 8;
        String out = "Value (" + value + ") (while) ->";
        while (value % 2 == 0) {
            out += " " + value;
            value /= 2;
        }
        System.out.println(out);

        value = 8;
        out = "Value (" + value + ") (do) ->";
        do {
            value /= 2;
            out += " " + value;
        } while (value % 2 == 0);
        System.out.println(out);

        final int[] list = {0, 1, 2, 3};
        int accumulator = 0;
        for (int i = 0; i < list.length; i++) {
            System.out.print(" " + list[i]);
            accumulator += list[i];
        }
        System.out.println();
        System.out.println("Suma (of " + Arrays.toString(list) + "): " + accumulator);

        int max = -1; // solo para listas con valores naturales
        for (int item : list) {
            if (item > max) {
                max = item;
            }
        }
        System.out.println("Máximo (of " + Arrays.toString(list) + "): " + max);

    }
}
