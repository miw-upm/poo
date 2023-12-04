package upm.command;

import java.util.HashMap;
import java.util.Map;

public class Invoker {

    private final Map<String, Order> orders;

    public Invoker() {
        this.orders = new HashMap<>();
    }

    public void add(Order order) {
        this.orders.put(order.name(), order);
    }

    public void execute(String name) {
        this.orders.get(name).execute();
    }

    public String[] keys() {
        return this.orders.keySet().toArray(new String[0]);
    }
}
