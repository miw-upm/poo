package upm.introduction.paradigm;

import java.util.Arrays;

public class Declarative {
    public static void main(String[] args) {

        final int[] array = {0, 1, 2, 3};
        int sum = Arrays.stream(array)
                .sum();  //declarative
        System.out.println("Suma: " + sum);

        int max = Arrays.stream(array)
                .max()              //declarative
                .orElse(-1);  //declarative
        System.out.println("Máximo: " + max);
    }
}
