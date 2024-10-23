package upm.poo.inheritances;

import upm.poo.inheritances.base.Padre;

public class Hijo extends Padre {
    private static final String P_2 = "B";

    public static void main(String[] args) {
        new Hijo().imprime();
        new Hijo().imprime3();
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
