package upm.introduction.collections;

import java.util.HashMap;
import java.util.Map;

public class MapSnippets {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "two");
        map.put(5, "five");
        map.put(1, "one");
        System.out.println("keys: " + map.keySet());
        System.out.println("values: " + map.values());
        map.remove(1);
        System.out.println("map(1): " + map.get(1));
        map.replace(5, "FIVE");
        System.out.println("values: " + map.values());
    }
}
