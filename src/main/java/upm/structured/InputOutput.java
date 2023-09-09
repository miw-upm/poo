package upm.structured;

import java.util.Scanner;

public class InputOutput {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print(" Nombre > ");
        String name = scanner.nextLine();
        System.out.print(" Edad > ");
        int age = scanner.nextInt();
        System.out.print(" Altura (metros) > ");
        double height = scanner.nextDouble();

        System.out.println("Nombre: " + name + ", Edad: " + age + ", Altura: " + height);

    }
}
