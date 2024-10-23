package upm.poo.static_;

public class MyClass {
    private static final String CONSTANT = "valor";
    private static int attribute;

    static {
        MyClass.attribute = 10;
    }


    private int instanceAttribute;

    public static void m2() {
        MyClass.attribute = 10;
    }

    public void m() {
        MyClass.attribute = 10;
        this.instanceAttribute = 10;
    }

}
