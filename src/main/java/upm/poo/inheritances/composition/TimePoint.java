package upm.poo.inheritances.composition;

import upm.poo.inheritances.Point;

public class TimePoint {
    private final Point point;
    private int seconds;

    public TimePoint(int x, int y, int seconds) {
        this.point = new Point(x, y);
        this.seconds = seconds;
    }

    public int getTime() {
        return this.seconds;
    }

    public void setTime(int time) {
        this.seconds = time;
    }

    public double velocity() {
        return this.point.module() / this.seconds;
    }

    public double module() {
        return this.point.module();
    }

    public double phase() {
        return this.point.phase();
    }

    public void translateXOrigin(int x) {
        this.point.translateXOrigin(x);
    }

    public void translateOrigin(Point point) {
        this.point.translateOrigin(point);
    }

    public int getX() {
        return this.point.getX();
    }

    public void setX(int x) {
        this.point.setX(x);
    }

    public int getY() {
        return this.point.getY();
    }

    public void setY(int y) {
        this.point.setY(y);
    }

    @Override
    public String toString() {
        return point + "TimePoint{seconds=" + seconds + '}';
    }
}
