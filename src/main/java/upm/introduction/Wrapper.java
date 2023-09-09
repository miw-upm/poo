package upm.introduction;

public class Wrapper {

    public static void main(String[] args) {

        Integer classInt = 8; // number = new Integer(8);
        int primitive = 5;
        classInt *= primitive + 16;
        System.out.println("classInt: " + classInt);

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
