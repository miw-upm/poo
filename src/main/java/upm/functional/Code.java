package upm.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Code {
    public static final List<Integer> INTEGER_LIST = List.of(3, -2, 0, 4);
    public static final List<String> STRING_LIST = List.of("3", "-2", "0", "4");


    public void consumer() {
        for (int i = 0; i < INTEGER_LIST.size(); i++) { //for i
            System.out.println(INTEGER_LIST.get(i));
        }

        for (int item : INTEGER_LIST) {  //for each
            System.out.println(item);
        }

        INTEGER_LIST.stream()
                .forEach(System.out::println);  //functional //item->System.out.println(item)
    }

    public void predicate() { // only positive values
        for (int i = 0; i < INTEGER_LIST.size(); i++) { //for i
            if (INTEGER_LIST.get(i) >= 0) {
                System.out.println(INTEGER_LIST.get(i));
            }
        }
        for (int item : INTEGER_LIST) {  //for each
            if (item >= 0) {
                System.out.println(item);
            }
        }
        INTEGER_LIST.stream() //functional
                .filter(item -> item >= 0)
                .forEach(System.out::println);
    }

    public void function() { // convierte a Integer, elimina 0 y *2
        List<Integer> result2 = new ArrayList<>();
        for (String item : STRING_LIST) {  //for each
            int intItem = Integer.parseInt(item);
            if (intItem != 0) {
                result2.add(intItem * 2);
            }
        }
        System.out.println(result2);

        List<Integer> result = STRING_LIST.stream() //functional
                .map(Integer::parseInt)
                .filter(item -> item != 0)
                .map(item -> item * 2)
                .toList();
        System.out.println(result);
    }

    public void suplier() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) { //for i
            result.add(i * 2);
        }
        System.out.println(result);

        List<Integer> result2 = IntStream
                .range(0, 10)
                .map(value -> value * 2)
                .boxed()
                .toList();
        System.out.println(result2);
    }

    public void average() {
        int sum = 0;
        for (int number : INTEGER_LIST) {
            sum += number;
        }
        double average = (double) sum / INTEGER_LIST.size();

        average = INTEGER_LIST.stream()
                .mapToDouble(Integer::intValue)
                .average()
                .orElseThrow(); // Manejo de caso vacío
    }

}
