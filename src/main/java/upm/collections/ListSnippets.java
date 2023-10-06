package upm.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListSnippets {
    public static void main(String[] args) {

        List<String> list1 = Arrays.asList("0", "1", "2"); //Immutable List
        System.out.println("List1: " + list1);
        System.out.println("List1[1]: " + list1.get(1));
        for (String item : list1) {
            System.out.println("item:" + item);
        }
        list1 = List.of("0", "1", "2"); //Java 9
        System.out.println("List1: " + list1);

        List<String> list2 = new ArrayList<>();  //mutable list
        list2.add("zero");
        list2.add("one");
        list2.set(1, "1");
        list2.add("two");
        list2.add(0, "-1");
        System.out.println("list2: " + list2);
        list2.remove(0);
        System.out.println("list2: " + list2);

        String[] array = {"0", "1", "2"};
        List<String> list3 = new ArrayList<>(Arrays.asList(array));
        list3.add("3");
        System.out.println("list3: " + list3);

        System.out.println("list3 contains '1': " + list3.contains("1")); //equals

    }
}
