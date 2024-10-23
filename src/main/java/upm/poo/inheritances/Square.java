package upm.poo.inheritances;

public class Square extends Figure {
    private final double side;

    public Square(String name, double side) {
        super(name);
        this.side = side;
    }

    @Override
    public double area() {
        return this.side * this.side;
    }

    @Override
    public double numberOfSides() {
        return 4;
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                '}';
    }
}
