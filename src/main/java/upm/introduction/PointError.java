package upm.introduction;

public class PointError {
    public int x;
    public int y;

    public PointError(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PointError() {
        this.x = 0;
        this.y = 0;
    }

    public double module(int x, int y) {
        return Math.sqrt((double) x * x + y * y);
    }

    public double phase() {
        return Math.atan((double) this.y / this.x);
    }

    public void translateXOrigin(int x) {
        this.x -= x;
    }

    public void translateOrigin(int x, int y) {
        this.x -= x;
        this.y -= y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
