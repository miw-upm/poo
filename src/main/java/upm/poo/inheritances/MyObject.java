package upm.poo.inheritances;

public class MyObject {//extends Object

    public MyObject() {
        super();
    }

    @Override  // es redundante, y nos asegura que sobrescribimos el método y que no hacemos sobrecarga
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
