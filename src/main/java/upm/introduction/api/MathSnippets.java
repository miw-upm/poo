package upm.introduction.api;

public class MathSnippets {
    public static void main(String[] args) {
        double num1 = 9.7;
        double num2 = -15.3;
        int num3 = 16;

        System.out.println("Math.round(" + num1 + ") = " + Math.round(num1));
        System.out.println("Math.abs(" + num2 + ") = " + Math.abs(num2));
        System.out.println("Math.max(" + num1 + ", " + num2 + ") = " + Math.max(num1, num2));
        System.out.println("Math.min(" + num1 + ", " + num2 + ") = " + Math.min(num1, num2));
        System.out.println("Math.pow(2, 3) = " + Math.pow(2, 3)); // 2^3 = 8
        System.out.println("Math.sqrt(" + num3 + ") = " + Math.sqrt(num3));
        System.out.println("Math.random() = " + Math.random());
        double radians = Math.PI;
        System.out.println("Math.toDegrees(" + radians + ") = " + Math.toDegrees(radians));
        double degrees = 180;
        System.out.println("Math.toRadians(" + degrees + ") = " + Math.toRadians(degrees));
        System.out.println("Math.sin(PI/2) = " + Math.sin(Math.PI / 2));
        System.out.println("Math.cos(0) = " + Math.cos(0));
        System.out.println("Math.tan(PI/4) = " + Math.tan(Math.PI / 4));
    }
}
