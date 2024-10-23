package upm.introduction.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSnippets {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Fecha actual: " + today);

        LocalDate customDate = LocalDate.of(2023, 9, 15);
        System.out.println("Fecha personalizada: " + customDate);

        LocalDate nextWeek = today.plusDays(7);
        System.out.println("Dentro de una semana: " + nextWeek);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter);
        System.out.println("Fecha formateada: " + formattedDate);
    }
}
