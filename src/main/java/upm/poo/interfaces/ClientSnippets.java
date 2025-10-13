package upm.poo.interfaces;

public class ClientSnippets {
    public static void main(String[] args) {
        System.out.println("MyInterface constant: " + MyInterface.MY_CONSTANT);
        MyInterface myInterface = new Server();
        Client client = new Client(myInterface);
        client.run();
        System.out.println("defaultMethod: " + myInterface.defaultMethod());
    }
}
