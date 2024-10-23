package upm.doo.functional;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Imperative {

    public static final Function<String, Integer> CONVERT_TO_INTEGER = param -> Integer.parseInt(param);
    public static final Function<String, Integer> CONVERT_TO_INTEGER2 = Integer::parseInt;

    public static final BiConsumer<String, String> PRINT = (p1, p2) -> System.out.println(p1 + p2);
    public static final BiConsumer<String, String> PRINT2 = (String p1, String p2) -> System.out.println(p1 + p2);

    public static final Predicate<String> SHORT = value -> {
        value = value.trim();
        return value.length() < 4;
    };

    public static final Supplier<String> DOTS = () -> "...";

    public Integer function(String param) {
        return Integer.parseInt(param);
    }

    public void consumer(String p1, String p2) {
        System.out.println(p1 + p2);
    }

    public Boolean predicate(String value) {
        value = value.trim();
        return value.length() < 4;
    }

    public String suplier() {
        return "...";
    }


}
