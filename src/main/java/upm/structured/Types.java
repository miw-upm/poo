package upm.structured;

import java.util.Arrays;

public class Types {
    public static void main(String[] args) {

        byte minValue = 0b0101_0101; // enteros
        short twoBytes = 0x1fff;
        int age = 0;
        long creditCard = 1234_1234_1234_1234L;

        float decimal = 0.34F;
        double bigger = 3.4E-8;

        boolean flag = true;

        char character = 'a';
        final char UNICODE_VALUE = '\u1234';  // constante

        String name = "My name is...";  // Class

        System.out.println("minValue (1B): " + minValue);
        System.out.println("twoBytes (2B): " + twoBytes);
        System.out.println("age (4B): " + age);
        System.out.println("creditCard (8B): " + creditCard);
        System.out.println("decimal: (4B)" + decimal);
        System.out.println("bigger: (8B)" + bigger);
        System.out.println("flag: " + flag);
        System.out.println("character: (2B)" + character);
        System.out.println("UNICODE: " + UNICODE_VALUE);
        System.out.println("name: " + name);

        int[] values = {1, 2, 3};
        System.out.println("values: " + values);
        System.out.println("values length: " + values.length);
        System.out.println("values: " + Arrays.toString(values));

        String[] p;
        String[] items = new String[10];  // 0..9, contenido es null
        items[3] = "third";
        System.out.println("items: " + Arrays.toString(items));

        int intValue = 1000;
        byte byteValue = (byte) intValue;
        intValue = byteValue;
        System.out.println("intValue: " + intValue);
    }
}
