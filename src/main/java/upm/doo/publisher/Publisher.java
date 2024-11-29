package upm.doo.publisher;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class Publisher {
    private final Set<Consumer<String>> consumers;
    private String message;

    public Publisher() {
        this.consumers = new HashSet<>();
    }

    public void subscribe(Consumer<String> consumer) {
        this.consumers.add(consumer);
    }

    public void unsubscribe(Consumer<String> consumer) {
        this.consumers.remove(consumer);
    }

    public void next(String message) {
        this.consumers.forEach(consumer -> consumer.accept(message));
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
        this.next(message);
    }
}
