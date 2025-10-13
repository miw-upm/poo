package upm.poo.interfaces;

public class Server implements MyInterface {
    @Override
    public String echo(int param) {
        return "param: " + param;
    }

    @Override
    public long round(double param) {
        return Math.round(param);
    }

    @Override
    public String defaultMethod() {
        return "defaultMethod of SERVER";
    }
}
