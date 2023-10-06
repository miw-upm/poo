package upm.interfaces;

public class MyClass implements MyInterface {
    @Override
    public String echo(int param) {
        return "param: " + param;
    }

    @Override
    public long round(double param) {
        return Math.round(param);
    }
}
