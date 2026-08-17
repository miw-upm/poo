package upm.exam;

public class Derivate extends Father{
    protected int id;

    protected Derivate(int id, String name, Float valor) {
        super(id, name, valor);
        this.id = id + 5;
    }
}
