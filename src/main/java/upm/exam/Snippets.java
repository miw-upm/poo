package upm.exam;

public class Snippets {
    public static void main(String[] args) {
        Base base = new Derivada();
        int resultado = base.calcular(5);
        System.out.println(resultado);
    }
}
