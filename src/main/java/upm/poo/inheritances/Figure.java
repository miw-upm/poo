package upm.poo.inheritances;

public abstract class Figure {

    private final String name;

    protected Figure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double area();

    public abstract double numberOfSides();

    public Figure largestArea(Figure other) {
        if (this.area() > other.area()) {
            return this;
        } else {
            return other;
        }
    }

}
