package upm.generics.without;

public class Box {
    private Object object;

    public void put(Object object) {
        this.object = object;
    }

    public Object get() {
        return this.object;
    }
}
