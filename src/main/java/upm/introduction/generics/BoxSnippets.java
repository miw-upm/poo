package upm.introduction.generics;

public class BoxSnippets {
    public static void main(String[] args) {
        Box<Integer> box = new Box<>();
        box.put(10);
        Integer integer = box.get();
        System.out.println(integer);
    }

}
