package upm.doo.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Code {
    public static final List<Integer> INTEGER_LIST = List.of(3, -2, 0, 4);
    public static final List<String> STRING_LIST = List.of("3", "-2", "0", "4");


    public void consumerForI(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public void consumerForEach(List<Integer> list) {
        for (int item : list) {  //for each
            System.out.println(item);
        }
    }

    public void consumer(List<Integer> list) {
        list.forEach(System.out::println);
    }

    public List<Integer> predicateForIOnlyPositive(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) >= 0) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    public List<Integer> predicateForEachOnlyPositive(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (int item : list) {
            if (item >= 0) {
                result.add(item);
            }
        }
        return result;
    }

    public Stream<Integer> predicateFunctionalPositive(Stream<Integer> stream) {
        return stream.filter(item -> item >= 0);
    }

    public List<Integer> functionForEachConvertToIntRemove0Multiply2(List<String> list) {
        List<Integer> result = new ArrayList<>();
        for (String item : list) {
            int intItem = Integer.parseInt(item);
            if (intItem != 0) {
                result.add(intItem * 2);
            }
        }
        return result;
    }

    public Stream<Integer> functionFunctionalConvertToIntRemove0Multiply2(Stream<String> stream) {
        return stream
                .map(Integer::parseInt)
                .filter(item -> item != 0)
                .map(item -> item * 2);
    }

    public List<Integer> suplierForI() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) { //for i
            result.add(i);
        }
        return (result);
    }

    public List<Integer> suplierFunctional() {
        return IntStream
                .range(0, 10)
                .boxed()
                .toList();
    }

    public double averageForEach(List<Integer> list) {
        if (list.isEmpty()) {
            return Double.NaN;
        }
        int sum = 0;
        for (int number : list) {
            sum += number;
        }
        return (double) sum / list.size();
    }

    public double averageFunctional(List<Integer> list) {
        return list.stream()
                .mapToDouble(Integer::intValue)
                .average()
                .orElse(Double.NaN);
    }

}
