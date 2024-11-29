package upm.doo.publisher;

public class Subscriber {
    private final Publisher publisher;

    public Subscriber(Publisher publisher) {
        this.publisher = publisher;
        this.publisher.subscribe(System.out::println);
    }
}
