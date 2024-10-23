package upm.poo.interfaces;

public class ClientSnippets {
    public static void main(String[] args) {
        System.out.println("MyInterface constant: " + MyInterface.MY_CONSTANT);
        Client client = new Client(new MyClass());
        client.run();
    }
}
