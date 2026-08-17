package upm.exam;

public interface InterfaceA {
    int CONSTANT = 3;

    int m2(int x);

    static int m2() {
        return CONSTANT * 5;
    }

}
