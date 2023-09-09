package upm.introduction;

public class PointSnippets {
    public static void main(String[] args) {
        System.out.println("new Point(2,3): " + new Point(2, 3));
        System.out.println("Module Point(0,1): " + new Point(0, 1).module());
        System.out.println("Module Point(1,1): " + new Point(1, 1).module());
        System.out.println("Phase (radian) Point(1,1): " + new Point(1, 1).phase());
        Point point = new Point(2, 0);
        point.translateXOrigin(2);
        System.out.println("TranslateX 2 of Point(2,0): " + point);
        point.translateOrigin(new Point(-1, -1));
        System.out.println("Translate (-1,-1) of Point(0,0): " + point);
    }
}
