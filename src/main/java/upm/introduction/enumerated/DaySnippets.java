package upm.introduction.enumerated;

import java.util.Arrays;

public class DaySnippets {
    public static void main(String[] args) {
        Day day = Day.MONDAY;
        System.out.println("Dia: " + day);
        System.out.println("Dias: " + Arrays.toString(Day.values()));
        System.out.println("Dia desde cadena: " + Day.valueOf("FRIDAY"));
        System.out.println("Ordinal del dia: " + day.ordinal());
    }
}
