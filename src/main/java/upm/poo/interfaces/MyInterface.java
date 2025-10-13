package upm.poo.interfaces;

public interface MyInterface {

    String MY_CONSTANT = "Mi constante"; //public static final

    String echo(int param); //public

    long round(double param);

    default String defaultMethod() {
        return "Default method";
    }

    default String defaultMethod2(MyInterface myInterface) {
        return "Default method 2: " + myInterface.echo(666) + myInterface.round(666);
    }
}
