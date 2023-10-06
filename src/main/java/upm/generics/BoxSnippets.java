package upm.generics;

public class BoxSnippets {
    public static void main(String[] args) {
        Box<Integer> intCaja = new Box<>();
        intCaja.put(10);
        Integer unInt = intCaja.get();
        System.out.println(unInt);
    }
}
