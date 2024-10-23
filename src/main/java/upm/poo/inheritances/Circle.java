package upm.poo.inheritances;

public class Circle extends Figure {

    private final double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * this.radius * this.radius;
    }

    @Override
    public double numberOfSides() {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
