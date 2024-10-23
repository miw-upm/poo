package upm.poo.interfaces;

public class Client {
    private final MyInterface myInterface;

    public Client(MyInterface myInterface) {
        this.myInterface = myInterface;
    }

    public void run() {
        this.myInterface.echo(666);
        this.myInterface.round(9.9);
        this.myInterface.defaultMethod();
        this.myInterface.defaultMethod2(myInterface);
    }
}
