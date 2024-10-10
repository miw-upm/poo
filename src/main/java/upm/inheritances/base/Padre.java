package upm.inheritances.base;

public class Padre {
    private static final String P_2 = "A";
    protected static String P_1 = "Soy un ";

    public void imprime() {
        imprime1();
    }

    void imprime1() {
        System.out.print(P_1 + P_2);
        imprime2();
    }

    private void imprime2() {
        System.out.print(";");
    }

    public void imprime3() {
        System.out.print(P_1 + P_2);
    }
}
