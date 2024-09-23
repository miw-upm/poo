package upm.api;

public class WrappersSnippets {

    public static void main(String[] args) {

        Integer classInt = 8; // number = Integer.valueOf(8);
        int primitive = 5;
        classInt *= primitive + 16;
        System.out.println("classInt: " + classInt);

        System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);

        System.out.println("Double.NEGATIVE_INFINITY" + Double.NEGATIVE_INFINITY);
        System.out.println("Double.POSITIVE_INFINITY" + Double.POSITIVE_INFINITY);
        System.out.println("Double.NaN" + Double.NaN);

        long time = System.currentTimeMillis();
        for (int i = 0; i < 10_000_000; i++) {
            classInt += 2;
        }
        long time2 = System.currentTimeMillis();
        System.out.println("classInt time: " + (time2 - time) + "ms");


        time = System.currentTimeMillis();
        for (int i = 0; i < 10_000_000; i++) {
            primitive += 2;
        }
        time2 = System.currentTimeMillis();
        System.out.println("primitive time: " + (time2 - time) + "ms");
    }

}
