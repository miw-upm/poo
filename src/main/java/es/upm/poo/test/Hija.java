package es.upm.poo.test;

public class Hija extends Padre{
    private String name;

    public Hija(Integer valor, String name) {
        super(valor);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
