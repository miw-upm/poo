package upm.exam;

public class Father {
    protected int id;
    protected Float valor;
    protected String name;

    protected Father(int id, String name, Float valor) {
        this.id = id;
        this.name = name;
        this.valor = valor;
    }

    public int getId() {
        return this.id;
    }

    public static void main(String[] args) {
        Father p1 = new Derivate(1, " P1 ", (Float) null);
        System.out.println(" This class has a ID " + p1.getId());
    }

}
