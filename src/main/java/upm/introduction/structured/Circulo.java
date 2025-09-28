package upm.introduction.structured;

public class Circulo {
    private final double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    public Circulo() {
        this(1.0);
    }

    public double area() {
        return Math.PI * Math.pow(this.radio, 2);
    }

    public double perimetro() {
        return 2 * Math.PI * this.radio;
    }

    public int numLados() {
        return 0;
    }

    public double getRadio() {
        return this.radio;
    }


}
