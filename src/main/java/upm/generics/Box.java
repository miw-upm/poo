package upm.generics;

public class Box<E> {
    private E element;

    public void put(E element) {
        this.element = element;
    }

    public E get() {
        return this.element;
    }
}
