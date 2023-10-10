package upm.optional;

import upm.introduction.Point;

import java.util.Optional;

public class OptionalSnippets {
    public static void main(String[] args) {
        Optional<Point> pointOptional = getPoint();
        if (pointOptional.isPresent()) {
            System.out.println("Esta presente:" + pointOptional.get());
        }
        pointOptional.orElse(new Point());
        pointOptional.orElseThrow(() -> new RuntimeException());
    }

    public static Optional<Point> getPoint() {
        return Optional.empty();
    }
}
