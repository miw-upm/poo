package upm.structured;

public class Operators {
    public static void main(String[] args) {

        int index = 0;
        System.out.println("index: " + index++);
        System.out.println("index: " + --index);

        boolean flag = true;
        System.out.println("flag: " + !flag);

        int value = 0;
        System.out.println("flag (Not binary): " + Integer.toBinaryString(~value));

        System.out.println("Cociente 3/2: " + 3 / 2);
        System.out.println("Resto 3%2: " + 3 % 2);
        System.out.println("Decimal 3/2: " + (double) 3 / 2);

        byte binary = -1;
        System.out.println("binary: " + Integer.toBinaryString(binary));
        System.out.println("binary << 2: " + Integer.toBinaryString(binary << 2));
        System.out.println("binary >> 2: " + Integer.toBinaryString(binary >> 2));
        System.out.println("binary >>> 2: " + Integer.toBinaryString(binary >>> 2));

        int x = 1;
        double y = 2;
        System.out.println("expression (1+x/y): " + (1 + x / y));
    }

}
