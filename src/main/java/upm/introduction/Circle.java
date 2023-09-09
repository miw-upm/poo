package upm.introduction;

public class Circle {
    public static final double PI = 3.141592653589793;
    private double radius;

    // Constructor
    public Circle(double radius) {
        this.radius = radius;
    }

    public double area() {
        return Circle.PI * this.radius * this.radius;
    }

    public double circumference() {
        return 2 * Circle.PI * this.radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}

