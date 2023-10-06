package upm.generics.without;

public class BoxSnippets {
    public static void main(String[] args) {
        Box intCaja = new Box();
        intCaja.put(10);
        Integer unInt = (Integer) intCaja.get();
        System.out.println(unInt);
    }
}
