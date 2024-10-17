package upm.exam.p3;

public class Base {
    private final int valor;

    public Base() {
        this.valor = 3;
    }

    public int calcular(int param) {
        return this.valor + param;
    }

    public int calcular() {
        return this.valor * 2;
    }

    public int getValor() {
        return valor;
    }
}
