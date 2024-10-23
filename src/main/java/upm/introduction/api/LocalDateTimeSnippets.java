package upm.introduction.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSnippets {
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Fecha y hora actual: " + currentDateTime);

        LocalDateTime customDateTime = LocalDateTime.of(2023, 9, 15, 14, 30);
        System.out.println("Fecha y hora personalizada: " + customDateTime);

        LocalDateTime twoHoursLater = currentDateTime.plusHours(2);
        System.out.println("Dentro de dos horas: " + twoHoursLater);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("Fecha y hora formateada: " + formattedDateTime);
    }
}
