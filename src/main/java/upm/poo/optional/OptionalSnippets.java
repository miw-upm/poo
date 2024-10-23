package upm.poo.optional;

import upm.introduction.Point;

import java.util.Optional;

public class OptionalSnippets {
    public static void main(String[] args) {
        Optional<Point> point = Optional.of(new Point());
        point = Optional.empty();

        if (point.isPresent()) {
            System.out.println("Esta presente:" + point.get());
        }
        Point aPoint = point.orElse(new Point());
        if (point.isEmpty()) {
            System.out.println("Esta vacio");
        }
        aPoint = point.orElseThrow(); //Throws: NoSuchElementException
        aPoint = point.orElseThrow(() -> new RuntimeException());
    }

    public static Optional<Point> getPoint() {
        return Optional.empty();
    }
}
