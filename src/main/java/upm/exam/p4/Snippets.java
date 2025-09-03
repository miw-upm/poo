package upm.exam.p4;

public class Snippets {
    public static void main(String[] args) {
        // B<A> b= new B<>(new A());
        B<CustomA> b = new B<>(new CustomA());
        //B<Object> b= new B<>(new B());
    }
}
